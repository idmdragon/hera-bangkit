package com.hera.bangkit.data.source.local
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.local.dao.HeraDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class LocalDataSource @Inject constructor(
    private val dao: HeraDao
){

    fun getAllItem(): Flow<List<StoryEntity>> = dao.getAllStory()
    suspend fun insertStory(item: List<StoryEntity>) = dao.insertStory(item)


}