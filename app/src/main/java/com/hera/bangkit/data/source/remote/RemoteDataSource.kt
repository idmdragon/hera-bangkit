package com.hera.bangkit.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.hera.bangkit.data.source.local.entity.ReportEntity
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.UserResponse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.lang.Exception
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

    fun getUser(uid: String): LiveData<RemoteResponse<UserResponse>> {
        val user = MutableLiveData<RemoteResponse<UserResponse>>()
        CoroutineScope(Dispatchers.IO).launch {
            val userSnapshot = userCollection
                .whereEqualTo("uid", uid)
                .get()
                .await()
            for (users in userSnapshot.documents) {
                val item = users.toObject<UserResponse>()
                if (item != null) {
                    user.postValue(RemoteResponse.success(item))
                }
            }
        }
        return user
    }

    fun getUserReport(): LiveData<RemoteResponse<ReportEntity>> {
        val listItems = MutableLiveData<RemoteResponse<ReportEntity>>()
        CoroutineScope(Dispatchers.IO).launch {
            val querySnapshot = reportCollection
                .whereEqualTo("fullname", "Ilham Dwi Muchlison")
                .get()
                .await()
            for (document in querySnapshot.documents) {
                val item = document.toObject<ReportEntity>()
                if (item != null) {
                    listItems.postValue(RemoteResponse.success(item))
                }
            }
        }
        return listItems

    }


    fun getStoryList(): Flow<ApiResponse<ArrayList<StoryEntity>>> {
        return flow {
            try {
                val listStory = ArrayList<StoryEntity>()
                val value = CoroutineScope(Dispatchers.IO).launch {
                    val querySnapshot = storyCollection
                        .get()
                        .await()
                    for (document in querySnapshot.documents) {
                        val item = document.toObject<StoryResponse>()
                        val userSnapshot = userCollection
                            .whereEqualTo("uid", item?.userID)
                            .get()
                            .await()
                        if (item != null) {
                            for (users in userSnapshot.documents) {
                                val userItem = users.toObject<UserResponse>()
                                if (userItem != null) {
                                    listStory.add(
                                        StoryEntity(
                                            userItem.avatar,
                                            item.category,
                                            item.content,
                                            item.imgContent,
                                            item.isLike,
                                            item.isUpvoted,
                                            item.like,
                                            item.timeUpload,
                                            userItem.username,
                                            item.upvote,
                                        )
                                    )

                                }
                            }

                        }
                    }

                }
                value.join()
                if (listStory.isNotEmpty()) {
                    emit(ApiResponse.Success(listStory))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("Error Exception", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }



}