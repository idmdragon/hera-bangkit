package com.hera.bangkit.ui.main.post.report

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.hera.bangkit.R
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.tflite.Classifier
import com.hera.bangkit.ui.main.MainActivity
import com.hera.bangkit.utils.DateHelper
import com.hera.bangkit.utils.DummyUser
import dagger.hilt.android.AndroidEntryPoint
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.*

@AndroidEntryPoint
class PostReportActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReportBinding
    private val viewModel : ReportViewModel by viewModels()

    // Name of TFLite model ( in /assets folder ).
    private val MODEL_ASSETS_PATH = "modelpost.tflite"
    private val INPUT_MAXLEN = 45
    private var tfLiteInterpreter : Interpreter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        val items = listOf("Kekerasan Fisik", "Kejahatan Seksual", "Exploitasi Ekonomi", "Penculikan, Penjualan, atau Perdagangan", "Pornografi","Perlakuan Salah & Penelantaran","Anak yang Berkonflik dengan Hukum","Penyalahgunaan NAPZA")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.kategoriMenu.setAdapter(adapter)
        //untuk mendapatkan tanggal DateHelper.getCurrentDate()

        //Tensorflow
        // Init the classifier.
        val classifier = Classifier( this , "word_dict_post.json" , INPUT_MAXLEN )
        // Init TFLiteInterpreter
        tfLiteInterpreter = Interpreter( loadModelFile() )

        val progressDialog = ProgressDialog( this )
        progressDialog.setMessage( "Parsing word_dict_post.json ..." )
        progressDialog.setCancelable( false )
        progressDialog.show()
        classifier.processVocab( object: Classifier.VocabCallback {
            override fun onVocabProcessed() {
                // Processing done, dismiss the progressDialog.
                progressDialog.dismiss()
            }
        })

        with(binding){
            btnPost.setOnClickListener {

                val desc = etDesc.editText?.text.toString()

                if (desc.isEmpty()){
                    etDesc.error= "Kronologi Tidak boleh kosong"
                }else{
                    val tokenizedMessage = classifier.tokenize(desc.toLowerCase(Locale.ROOT).trim())
                    val results = classifySequence(tokenizedMessage)

                    val highest = results.maxOrNull()
                    val idxLabel = results.indexOfFirst { it == highest!! }
                    val category = findLabel(idxLabel)
                    startActivity(Intent(this@PostReportActivity,MainActivity::class.java)).also {
                        finish()
                    }
//                    postData(desc,category)
                    Toast.makeText(this@PostReportActivity,"Isi Label $category",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun postData(desc: String, category: String) {
        val dummyUser = DummyUser.generateUser()
        val reportItem = ReportEntity(
            dummyUser.address,
            category,
            desc,
            DummyUser.generateUser().Fullname,
            dummyUser.NIK,
            dummyUser.DateOfBirth,
            dummyUser.PhoneNumber,
            DateHelper.getCurrentDate()
        )


        viewModel.insertReport(reportItem)
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
            0 -> label = "Eksploitasi"
            1 -> label = "Eksploitasi Seksual"
            2 -> label = "Kekerasan Fisik"
            3 -> label = "Kekerasan Lainnya"
            4 -> label = "Kekerasan Psikis"
            5 -> label = "Kekerasan Seksual"
            6 -> label = "Penelantaran"
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
        val outputs : Array<FloatArray> = arrayOf( FloatArray( 7 ) )
        tfLiteInterpreter?.run( inputs , outputs )
        return outputs[0]
    }
}