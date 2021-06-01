package com.hera.bangkit.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.data.response.UserEntity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(
    @Named("stories")
    private val storyCollection: CollectionReference,
    @Named("report")
    private val reportCollection: CollectionReference,
    @Named("users")
    private val userCollection: CollectionReference

) {

    fun insertStory(storyEntity: StoryResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            storyCollection.add(storyEntity).await()
        }
    }

    fun insertReport(reportEntity: ReportEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            reportCollection.add(reportEntity).await()
        }
    }

    fun insertUser(user: UserEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            userCollection.add(user).await()
        }
    }
    fun getUserReport():LiveData<RemoteResponse<ReportEntity>>{
        val listItems = MutableLiveData<RemoteResponse<ReportEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = reportCollection
                .whereEqualTo("fullname","Ilham Dwi Muchlison")
                .get()
                .await()
            for(document in querySnapshot.documents){
                val item = document.toObject<ReportEntity>()
                if (item!=null){
                    listItems.postValue(RemoteResponse.success(item))
                }
            }
        }
        return listItems


    }



}