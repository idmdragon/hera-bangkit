package com.hera.bangkit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.utils.DummyUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class StoryViewModel : ViewModel() {

    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()


    fun getStoryList(): LiveData<ArrayList<StoryEntity>> {
        val listItem = ArrayList<StoryEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("stories")
                .get()
                .await()

            for(document in querySnapshot.documents){
                val item = document.toObject<StoryResponse>()
                //dibawah sini nanti bikin collection ke user UID buat get kek ava sama username
                Log.d("HomeViewModel","Isi Item $item")
                val user = DummyUser.generateUser()
                if (item != null) {
                    listItem.add(
                        StoryEntity(
                            "https://image.flaticon.com/icons/png/512/194/194938.png",
                            item.category,
                            item.content,
                            item.imgContent,
                            item.isLike,
                            item.like,
                            item.location,
                            item.timeUpload,
                            user.Username,
                        )
                    )
                }
            }
            _listStory.postValue(listItem)
        }
        return _listStory
    }

    fun getStoryWithTag(category : String): LiveData<ArrayList<StoryEntity>> {
        val listItem = ArrayList<StoryEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("stories")
                .whereEqualTo("category",category)
                .get()
                .await()

            for(document in querySnapshot.documents){
                val item = document.toObject<StoryResponse>()
                //dibawah sini nanti bikin collection ke user UID buat get kek ava sama username
                Log.d("HomeViewModel","Isi Item $item")
                val user = DummyUser.generateUser()
                if (item != null) {
                    listItem.add(
                        StoryEntity(
                            "https://image.flaticon.com/icons/png/512/194/194938.png",
                            item.category,
                            item.content,
                            item.imgContent,
                            item.isLike,
                            item.like,
                            item.location,
                            item.timeUpload,
                            user.Username,
                        )
                    )
                }
            }
            _listStory.postValue(listItem)
        }
        return _listStory
    }
}