package com.hera.bangkit.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.toObject
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.utils.DummyUser
import com.idm.moviedb.data.source.remote.RemoteResponse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(
    @Named("story")
    private val storyCollection: CollectionReference,
    @Named("report")
    private val reportCollection: CollectionReference,

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

    fun getUserReport():LiveData<RemoteResponse<ReportEntity>>{
        val listItems = MutableLiveData<RemoteResponse<ReportEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = reportCollection
                .whereEqualTo("fullname",DummyUser.generateUser().Fullname)
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