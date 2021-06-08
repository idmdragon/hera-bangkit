package com.hera.bangkit.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.repositories.DefaultRepository
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.data.source.remote.response.UserResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


@HiltViewModel
class StoryViewModel @Inject constructor(
    private val repository: DefaultRepository
): ViewModel() {


    fun getStoryList() = repository.getListStory().asLiveData()
    fun getStoryByHastag(hastag : String) = repository.getStorybyHastag(hastag).asLiveData()


    fun increaseStory(story: StoryEntity) {
        val storyCollectionRef = Firebase.firestore.collection("stories")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("category", story.category)
                .whereEqualTo("content", story.content)
                .whereEqualTo("timeUpload", story.timeUpload)
                .get()
                .await()
            for (document in querySnapshot) {
                val storyRef = storyCollectionRef.document(document.id)
                    storyRef.update("like", FieldValue.increment(1))
                    storyRef.update("isLike", true)
            }
        }

    }
    fun decreaseStory(story: StoryEntity) {
        val storyCollectionRef = Firebase.firestore.collection("stories")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("category", story.category)
                .whereEqualTo("content", story.content)
                .whereEqualTo("timeUpload", story.timeUpload)
                .get()
                .await()
            for (document in querySnapshot) {
                val storyRef = storyCollectionRef.document(document.id)
                storyRef.update("like", FieldValue.increment(-1))
                storyRef.update("isLike", false)
            }
        }

    }
    fun increaseUpvote(story: StoryEntity) {
        val storyCollectionRef = Firebase.firestore.collection("stories")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("category", story.category)
                .whereEqualTo("content", story.content)
                .whereEqualTo("timeUpload", story.timeUpload)
                .get()
                .await()
            for (document in querySnapshot) {
                val storyRef = storyCollectionRef.document(document.id)
                storyRef.update("upvote", FieldValue.increment(1))
                storyRef.update("isUpvoted", true)
            }
        }

    }
    fun decreaseUpvote(story: StoryEntity) {
        val storyCollectionRef = Firebase.firestore.collection("stories")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = storyCollectionRef
                .whereEqualTo("category", story.category)
                .whereEqualTo("content", story.content)
                .whereEqualTo("timeUpload", story.timeUpload)
                .get()
                .await()
            for (document in querySnapshot) {
                val storyRef = storyCollectionRef.document(document.id)
                storyRef.update("upvote", FieldValue.increment(-1))
                storyRef.update("isUpvoted", false)
            }
        }

    }
    fun reportStory(story: StoryEntity) {
        val reportCollectionRef = Firebase.firestore.collection("reported")
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = reportCollectionRef
                .get()
                .await()
            for (document in querySnapshot) {
                val storyRef = reportCollectionRef.document(document.id)
                storyRef.update("isUpvoted", false)
            }
        }

    }
//    fun getStoryWithTag(category: String): LiveData<ArrayList<StoryEntity>> {
//        val listItem = ArrayList<StoryEntity>()
//        CoroutineScope(Dispatchers.IO).launch {
//            val querySnapshot = Firebase.firestore.collection("stories")
//                .whereEqualTo("category", category)
//                .get()
//                .await()
//
//            for (document in querySnapshot.documents) {
//                val item = document.toObject<StoryResponse>()
//                val userSnapshot = Firebase.firestore.collection("users")
//                    .whereEqualTo("uid", item?.userID)
//                    .get()
//                    .await()
//                if (item != null) {
//                    for (users in userSnapshot.documents) {
//                        val userItem = users.toObject<UserResponse>()
//                        if (userItem != null) {
//                            listItem.add(
//                                StoryEntity(
//                                    userItem.avatar,
//                                    item.category,
//                                    item.content,
//                                    item.imgContent,
//                                    item.isLike,
//                                    item.isUpvoted,
//                                    item.like,
//                                    item.timeUpload,
//                                    userItem.username,
//                                    item.upvote,
//                                )
//                            )
//                        }
//
//                    }
//
//                }
//            }
//            _listStory.postValue(listItem)
//        }
//        return _listStory
//    }
}