package com.hera.bangkit.data.repositories

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.entity.UserEntity
import com.idm.moviedb.vo.Resource

interface DataSource {
    fun insertStory(story : StoryResponse)
    fun getListStory(): LiveData<Resource<PagedList<StoryResponse>>>

    fun insertReport(report : ReportEntity)
    fun getListReport(): LiveData<Resource<PagedList<ReportEntity>>>

    fun insertUser(user : UserEntity)
}