package com.hera.bangkit.tflite

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.util.*
import kotlin.collections.HashMap

class Classifier(context: Context, jsonFilename: String) {

    private var context: Context? = context
    private var filename: String? = jsonFilename
    private var vocabData: HashMap<String, Int>? = null

    /**
     * A method to load the contents of the vocab (word_dict.json)
     * @param filename json file name
     * @return String
     */
    private fun loadJSONFromAsset(filename: String?): String? {
        val json: String?
        try {
            val inputStream = context!!.assets.open(filename!!)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    /**
     * A method to process the vocabulary
     */
    fun processVocab() {
        loadVocab(loadJSONFromAsset(filename)!!)
    }

    /**
     * A method to tokenize (split sentence into words) the text and convert it to int based on the word dictionary
     * @param message the text that the user want to predict
     * @return IntArray
     */
    fun tokenize(message: String): IntArray {
        val parts: List<String> = message.split(" ")
        val tokenizedMessage = ArrayList<Int>()
        for (part in parts) {
            if (part.trim() != "") {
                val index: Int? = if (vocabData!![part] == null) {
                    0
                } else {
                    vocabData!![part]
                }
                tokenizedMessage.add(index!!)
            }
        }
        return tokenizedMessage.toIntArray()
    }

    /**
     * A method to pad the given sequence (to a fixed & same length) with zeros
     * @param sequence the sequence of the text
     * @return IntArray
     */
    fun padSequence(sequence: IntArray): IntArray {
        val maxlen = 50
        return when {
            sequence.size > maxlen -> {
                sequence.sliceArray(0..maxlen)
            }
            sequence.size < maxlen -> {
                val array = ArrayList<Int>()
                array.addAll(sequence.asList())
                for (i in array.size until maxlen) {
                    array.add(0)
                }
                array.toIntArray()
            }
            else -> {
                sequence
            }
        }
    }

    /**
     * A method to load the vocabulary
     * @param json
     */
    private fun loadVocab(json: String) {
        val jsonObject = JSONObject(json)
        val iterator: Iterator<String> = jsonObject.keys()
        val data = HashMap<String, Int>()
        while (iterator.hasNext()) {
            val key = iterator.next()
            data[key] = jsonObject.get(key) as Int
        }
        vocabData = data
    }

}