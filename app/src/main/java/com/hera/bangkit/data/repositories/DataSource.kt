package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hera.bangkit.data.source.Resource
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.local.entity.ReportEntity
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.UserResponse
import com.hera.bangkit.data.source.remote.RemoteResponse
import kotlinx.coroutines.flow.Flow

interface DataSource {
    fun insertStory(story : StoryResponse)
    fun getListStory(): Flow<Resource<List<StoryEntity>>>

    fun insertReport(report : ReportEntity)

    fun insertUser(user : UserEntity)

    fun getUser(uid : String) : LiveData<RemoteResponse<UserResponse>>
}