package com.hera.bangkit.ui.main.post.story

import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.core.view.isEmpty
import androidx.core.view.isVisible
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.databinding.ActivityStoryBinding
import com.hera.bangkit.tflite.Classifier
import com.hera.bangkit.utils.DateHelper
import com.theartofdev.edmodo.cropper.CropImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

@AndroidEntryPoint
class PostStoryActivity : AppCompatActivity() {

    private val cropActivityResultContract = object :ActivityResultContract<Any?, Uri?>(){
        override fun createIntent(context: Context, input: Any?): Intent {
            return CropImage.activity()
                .setAspectRatio(16,16)
                .getIntent(this@PostStoryActivity)
        }

        override fun parseResult(resultCode: Int, intent: Intent?): Uri? {
             return CropImage.getActivityResult(intent)?.uri
        }
    }

    private lateinit var cropActivityResultLauncher : ActivityResultLauncher<Any?>

    private lateinit var binding: ActivityStoryBinding
    private val viewModel: PostStoryViewModel by viewModels()

    // Name of TFLite model ( in /assets folder ).
    private val MODEL_ASSETS_PATH = "model.tflite"
    private val INPUT_MAXLEN = 45
    private var tfLiteInterpreter : Interpreter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cropActivityResultLauncher = registerForActivityResult(cropActivityResultContract){
            it?.let { uri ->
                with(binding){
                    val textGambar = "Ubah Gambar"
                    ivContent.isVisible = true
                    btnAddImg.text = textGambar
                    ivContent.setImageURI(uri)
                }
            }
        }

        binding.btnAddImg.setOnClickListener {
            cropActivityResultLauncher.launch{null}
        }


        //Tensorflow
        // Init the classifier.
        val classifier = Classifier( this , "word_dict.json" , INPUT_MAXLEN )
        // Init TFLiteInterpreter
        tfLiteInterpreter = Interpreter( loadModelFile() )

        val progressDialog = ProgressDialog( this )
        progressDialog.setMessage( "Parsing word_dict.json ..." )
        progressDialog.setCancelable( false )
        progressDialog.show()
        classifier.processVocab( object: Classifier.VocabCallback {
            override fun onVocabProcessed() {
                // Processing done, dismiss the progressDialog.
                progressDialog.dismiss()
            }
        })


        with(binding) {
            btnPost.setOnClickListener {
                val content = tvContent.editText?.text.toString()
                if (content.isEmpty()) {
                    tvContent.error = "Text Field Tidak Boleh Kosong"
                    tvContent.error = null
                } else {

                    val tokenizedMessage = classifier.tokenize(content.toLowerCase(Locale.ROOT).trim())
                    val results = classifySequence(tokenizedMessage)

                    val highest = results.maxOrNull()
                    val idxLabel = results.indexOfFirst { it == highest!! }
                    val finalLabel = findLabel(idxLabel)

                    val storyItem = StoryEntity(
                        "nwDaazUrlPY3iDPDY0xn2TxoL703",
                        DateHelper.getCurrentDate(),
                        finalLabel,
                        content,
                        "",
                        0
                    )

                    viewModel.insertStory(storyItem)
                    Toast.makeText(
                        this@PostStoryActivity,
                        "Cerita Berhasil di Kirimkan",
                        Toast.LENGTH_LONG
                    ).show()
                }

            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }

    }



//  <!--------------Tensor Flow Function---------->

    /**
     * A method to find the label of the predicted text
     * @param idx Index of the highest label
     * @return String of the label
     */
    fun findLabel(idx: Int): String {
        var label = ""
        when(idx){
            0 -> label = "Cyber"
            1 -> label = "Educational Places"
            2 -> label = "Neutral"
            3 -> label = "Private Places"
            4 -> label = "Public Places"
            5 -> label = "Work Places"
        }
        return label
    }


    /**
     * A method to load the TFLite Model
     * @throws IOException
     * @return MappedByteBuffer
     */
    @Throws(IOException::class)
    private fun loadModelFile(): MappedByteBuffer {
        val assetFileDescriptor = assets.openFd(MODEL_ASSETS_PATH)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declaredLength = assetFileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
    }

    /**
     * A method to classify and generate probability of every class of the predicted text
     * @param sequence array of tokenized text
     * @return FloatArray
     */
    // Perform inference, given the input sequence.
    private fun classifySequence (sequence : IntArray ): FloatArray {
        // Input shape -> ( 1 , INPUT_MAXLEN )
        val inputs : Array<FloatArray> = arrayOf( sequence.map { it.toFloat() }.toFloatArray() )
        // Output shape -> ( 1 , 6 ) ( as numClasses = 6 )
        val outputs : Array<FloatArray> = arrayOf( FloatArray( 6 ) )
        tfLiteInterpreter?.run( inputs , outputs )
        return outputs[0]
    }
}