package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import com.hera.bangkit.data.NetworkBoundResource
import com.hera.bangkit.data.source.Resource
import com.hera.bangkit.data.source.local.entity.ReportEntity
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.remote.response.StoryResponse
import com.hera.bangkit.data.source.local.entity.UserEntity
import com.hera.bangkit.data.source.remote.response.UserResponse
import com.hera.bangkit.data.source.local.LocalDataSource
import com.hera.bangkit.data.source.remote.ApiResponse
import com.hera.bangkit.data.source.remote.RemoteDataSource
import com.hera.bangkit.data.source.remote.RemoteResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : DataSource {

    override fun insertStory(story: StoryResponse) {
        remoteDataSource.insertStory(story)
    }


    override fun getListStory(): Flow<Resource<List<StoryEntity>>> =
        object : NetworkBoundResource<List<StoryEntity>, ArrayList<StoryEntity>>() {
            override fun loadFromDB(): Flow<List<StoryEntity>> {
                return localDataSource.getAllItem()
            }
            override fun shouldFetch(data: List<StoryEntity>?): Boolean {
                return true
            }


            override suspend fun createCall(): Flow<ApiResponse<ArrayList<StoryEntity>>> =
                remoteDataSource.getStoryList()

            override suspend fun saveCallResult(data: ArrayList<StoryEntity>) {
                localDataSource.insertStory(data)
            }
        }.asFlow()

    override fun insertReport(report: ReportEntity) {
        remoteDataSource.insertReport(report)
    }




    override fun insertUser(user: UserEntity) {
        remoteDataSource.insertUser(user)
    }

    override fun getUser(uid: String): LiveData<RemoteResponse<UserResponse>> {
        return remoteDataSource.getUser(uid)
    }
}