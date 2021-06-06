package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hera.bangkit.data.NetworkBoundResource
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.data.entity.UserEntity
import com.hera.bangkit.data.response.UserResponse
import com.hera.bangkit.data.source.remote.RemoteDataSource
import com.hera.bangkit.data.source.remote.RemoteResponse
import com.hera.bangkit.vo.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : DataSource {

    override fun insertStory(story: StoryResponse) {
        remoteDataSource.insertStory(story)
    }

    override fun getListStory(): LiveData<Resource<PagedList<StoryResponse>>> {
        TODO("Not yet implemented")
    }

    override fun insertReport(report: ReportEntity) {
        remoteDataSource.insertReport(report)
    }

    override fun getListReport(): LiveData<Resource<PagedList<ReportEntity>>> {
        return object : NetworkBoundResource<PagedList<ReportEntity>, ReportEntity>() {

            val listReportItems = ArrayList<ReportEntity>()

            override fun loadFromDB(): LiveData<PagedList<ReportEntity>> {
                TODO("Not yet implemented")
            }

            override fun shouldFetch(data: PagedList<ReportEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<RemoteResponse<ReportEntity>> {
                return remoteDataSource.getUserReport()
            }

            override fun saveCallResult(data: ReportEntity) {
                TODO("Not yet implemented")
            }

        }.asLiveData()

    }

    override fun insertUser(user: UserEntity) {
        remoteDataSource.insertUser(user)
    }

    override fun getUser(uid: String): LiveData<RemoteResponse<UserResponse>> {
        return remoteDataSource.getUser(uid)
    }
}