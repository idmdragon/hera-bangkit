package com.hera.bangkit.ui.main.hastag

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.HastagEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.data.response.UserResponse
import com.hera.bangkit.utils.DummyHastag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HastagViewModel : ViewModel() {

    fun getHastag(arrHastag: Array<String>): LiveData<ArrayList<HastagEntity>> {
        val _listHastag = MutableLiveData<ArrayList<HastagEntity>>()
        val listHastag = ArrayList<HastagEntity>()

            CoroutineScope(Dispatchers.IO).launch {
                for (tag in arrHastag) {
                val querySnapshot = Firebase.firestore.collection("stories")
                    .whereEqualTo("category", tag)
                    .get()
                    .await()

                val item = HastagEntity(tag, querySnapshot.documents.size)
                    listHastag.add(item)
                }
                listHastag.sortByDescending { it.Count }
                _listHastag.postValue(listHastag)

        }

        return _listHastag
    }

}