package com.hera.bangkit.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hera.bangkit.data.source.local.entity.StoryEntity
import kotlinx.coroutines.flow.Flow
@Dao
interface HeraDao {
    @Query("SELECT * FROM story_list")
    fun getAllStory(): Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(onePiece: List<StoryEntity>)
}