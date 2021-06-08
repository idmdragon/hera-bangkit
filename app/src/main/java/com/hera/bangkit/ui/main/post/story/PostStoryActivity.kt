package com.hera.bangkit.ui.main.post.story

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.databinding.ActivityStoryBinding
import com.hera.bangkit.tflite.Classifier
import com.hera.bangkit.utils.DateHelper
import com.share424.sastrawi.Stemmer.StemmerFactory
import com.share424.sastrawi.StopWordRemover.StopWordRemoverFactory
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

@AndroidEntryPoint
class PostStoryActivity : AppCompatActivity() {


    private lateinit var binding: ActivityStoryBinding
    private val viewModel: PostStoryViewModel by viewModels()

    var photoMax: Int = 1
    var photoLocation: Uri? = null
    lateinit var storageRef: StorageReference
    var profilePic: String = ""

    private val modelAssetPath = "modelpost.tflite"
    private var tfLiteInterpreter: Interpreter? = null

    private val firebaseAuth = FirebaseAuth.getInstance()

    //Get Image
    private fun getFileExtension(uri: Uri?): String? {
        val contentResolver = contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri!!))
    }

    private fun findPhoto() {
        val pic = Intent()
        pic.type = "image/*"
        pic.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(pic, photoMax)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == photoMax && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            photoLocation = data.data
            Glide.with(this@PostStoryActivity)
                .load(photoLocation)
                .into(binding.ivContent)
            binding.ivContent.isVisible = true
            uploadImage()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddImg.setOnClickListener {
            findPhoto()
        }
        binding.btnBack.setOnClickListener {
            finish()
        }


        uploadImage()

        with(binding) {
            btnPost.setOnClickListener {
                val content = tvContent.editText?.text.toString()
                if (content.isEmpty()) {
                    tvContent.error = "Text Field Tidak Boleh Kosong"
                } else {
                    tvContent.error = null
                    progressBar.visibility = View.VISIBLE
                    postStory(content)
                }
            }
        }
    }

    private fun postStory(content: String) {
        val uid = firebaseAuth.currentUser?.uid
        val storyItem = StoryResponse(
            getLabel(content),
            content,
            profilePic,
            false,
            false,
            0,
            DateHelper.getCurrentDate(),
            uid!!,
            0
        )
        viewModel.insertStory(storyItem)
        Toast.makeText(
            this@PostStoryActivity,
            "Cerita berhasil di Post",
            Toast.LENGTH_LONG
        ).show()
        finish()
    }

    private fun getLabel(desc: String): String {
        val stemmer = StemmerFactory(applicationContext).create()
        val stopwordRemover = StopWordRemoverFactory(applicationContext).create()
        val classifier = Classifier(this, "word_dict_post.json")
        val options = Interpreter.Options()
        tfLiteInterpreter = Interpreter(loadModelFile(), options)
        classifier.processVocab()

        val stemmed = stemmer.stem(
            desc.toLowerCase(Locale.ROOT).trim()
        )
        val stopword = stopwordRemover.remove(stemmed)

        val tokenizedMessage = classifier.tokenize(stopword)
        val paddedMessage = classifier.padSequence(tokenizedMessage)
        val results = classifySequence(paddedMessage)

        val highest = results.maxOrNull()
        val idxLabel = results.indexOfFirst { it == highest!! }
        return findLabel(idxLabel)
    }

    private fun findLabel(idx: Int): String {
        var label = ""
        when (idx) {
            0 -> label = "Lokasi Kerja"
            1 -> label = "Lokasi Pendidikan"
            2 -> label = "Lokasi Privat"
            3 -> label = "Lokasi Publik"
            4 -> label = "Netral"
            5 -> label = "Siber"
        }
        return label
    }

    private fun uploadImage() {
        if (photoLocation != null) {
            storageRef = FirebaseStorage.getInstance().reference.child("ImageContent")
            val storage = storageRef.child(
                System.currentTimeMillis()
                    .toString() + "." + getFileExtension(photoLocation)
            )
            photoLocation?.let { it1 ->
                storage.putFile(it1).addOnSuccessListener {
                    storage.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> { uri ->
                        profilePic = uri.toString()
                    })
                }
            }
        }
    }


    @Throws(IOException::class)
    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = assets.openFd(modelAssetPath)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    private fun classifySequence(sequence: IntArray): FloatArray {
        //Input shape -> (1 , inputPaddedSequenceLength) -> (1,50)
        val inputs: Array<FloatArray> = arrayOf(sequence.map { it.toFloat() }.toFloatArray())
        //Output shape -> (1,6) (as numClasses = 6)
        val outputs: Array<FloatArray> = arrayOf(FloatArray(6))
        tfLiteInterpreter?.run(inputs, outputs)
        return outputs[0]
    }


}

