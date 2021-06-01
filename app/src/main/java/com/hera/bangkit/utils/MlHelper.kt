package com.hera.bangkit.utils

import android.app.ProgressDialog
import android.content.Context
import com.hera.bangkit.tflite.Classifier
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel

class MlHelper constructor(context: Context) {
//    private var context: Context? =  context
//    // Name of TFLite model ( in /assets folder ).
//    private val MODEL_ASSETS_PATH = "model.tflite"
//
//    // Max Length of input sequence. The input shape for the model will be ( None , INPUT_MAXLEN ).
//    private val INPUT_MAXLEN = 45
//
//    private var tfLiteInterpreter: Interpreter? = null
//
//    fun NlpCategory(content: String): String? {
//
//        //Tensorflow
//        // Init the classifier.
//        val classifier = context?.let { Classifier(it, "word_dict.json", INPUT_MAXLEN) }
//        // Init TFLiteInterpreter
//        tfLiteInterpreter = Interpreter(loadModelFile())
//
//        val progressDialog = ProgressDialog(context)
//        progressDialog.setMessage("Parsing word_dict.json ...")
//        progressDialog.setCancelable(false)
//        progressDialog.show()
//        classifier?.processVocab(object : Classifier.VocabCallback {
//            override fun onVocabProcessed() {
//                // Processing done, dismiss the progressDialog.
//                progressDialog.dismiss()
//            }
//        })
//
//        val tokenizedMessage = classifier?.tokenize(content.toLowerCase().trim())
//        val results = tokenizedMessage?.let { classifySequence(it) }
//
//        val highest = results?.maxOrNull()
//        val idxLabel = results?.indexOfFirst { it == highest!! }
//
//        return idxLabel?.let { findLabel(it) }
//    }
//
//    /**
//     * A method to find the label of the predicted text
//     * @param idx Index of the highest label
//     * @return String of the label
//     */
//    fun findLabel(idx: Int): String {
//        var label = ""
//        when (idx) {
//            0 -> label = "Cyber"
//            1 -> label = "Educational Places"
//            2 -> label = "Neutral"
//            3 -> label = "Private Places"
//            4 -> label = "Public Places"
//            5 -> label = "Work Places"
//        }
//        return label
//    }
//
//
//
//    /**
//     * A method to load the TFLite Model
//     * @throws IOException
//     * @return MappedByteBuffer
//     */
//    @Throws(IOException::class)
//    private fun loadModelFile(): MappedByteBuffer {
//        context.let {
//            val assetFileDescriptor = assets.openFd(MODEL_ASSETS_PATH)
//            val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
//            val fileChannel = fileInputStream.channel
//            val startOffset = assetFileDescriptor.startOffset
//            val declaredLength = assetFileDescriptor.declaredLength
//            return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength)
//        }
//
//    }
//
//    /**
//     * A method to classify and generate probability of every class of the predicted text
//     * @param sequence array of tokenized text
//     * @return FloatArray
//     */
//    // Perform inference, given the input sequence.
//    private fun classifySequence(sequence: IntArray): FloatArray {
//        // Input shape -> ( 1 , INPUT_MAXLEN )
//        val inputs: Array<FloatArray> = arrayOf(sequence.map { it.toFloat() }.toFloatArray())
//        // Output shape -> ( 1 , 6 ) ( as numClasses = 6 )
//        val outputs: Array<FloatArray> = arrayOf(FloatArray(6))
//        tfLiteInterpreter?.run(inputs, outputs)
//        return outputs[0]
//    }
}