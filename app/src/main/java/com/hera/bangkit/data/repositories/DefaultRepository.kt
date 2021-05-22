package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hera.bangkit.data.entity.StoryEntity
import com.idm.moviedb.data.source.remote.RemoteDataSource
import com.idm.moviedb.vo.Resource
import javax.inject.Inject

class DefaultRepository @Inject constructor (
    private val remoteDataSource: RemoteDataSource,
        ) : DataSource{

    override fun insertStory(story: StoryEntity) {
         remoteDataSource.insertStory(story)
    }

    override fun getListStory(): LiveData<Resource<PagedList<StoryEntity>>> {
        TODO("Not yet implemented")
    }
}