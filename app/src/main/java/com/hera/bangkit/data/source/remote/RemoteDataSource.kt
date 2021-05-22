package com.idm.moviedb.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentReference
import com.hera.bangkit.data.entity.StoryEntity

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named


class RemoteDataSource @Inject constructor(
    @Named("story")
    private val storyCollection: DocumentReference,
    @Named("report")
    private val reportCollection: DocumentReference,

    ) {

    fun insertStory(storyEntity: StoryEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            storyCollection.set(storyEntity).await()
        }
    }

}