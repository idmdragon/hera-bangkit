package com.hera.bangkit.ui.main.profile.story

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.data.response.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileStoryViewModel : ViewModel() {

    private val _listStory = MutableLiveData<ArrayList<StoryEntity>>()
    fun getMyStory(uid : String): LiveData<ArrayList<StoryEntity>> {
        val listItem = ArrayList<StoryEntity>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = Firebase.firestore.collection("stories")
                .whereEqualTo("userID",uid)
                .get()
                .await()

            for (document in querySnapshot.documents) {
                val item = document.toObject<StoryResponse>()
                val userSnapshot = Firebase.firestore.collection("users")
                    .whereEqualTo("uid",item?.userID)
                    .get()
                    .await()
                if (item != null) {
                    for (users in userSnapshot.documents) {
                        val userItem = users.toObject<UserResponse>()
                        if (userItem != null) {
                            listItem.add(
                                StoryEntity(
                                    userItem.avatar,
                                    item.category,
                                    item.content,
                                    item.imgContent,
                                    item.isLike,
                                    item.like,
                                    item.location,
                                    item.timeUpload,
                                    userItem.username,
                                )
                            )
                        }

                    }

                }
            }
            _listStory.postValue(listItem)
        }
        return _listStory
    }
}