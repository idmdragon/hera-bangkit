package com.hera.bangkit.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.data.source.local.dao.HeraDao

@Database(
    entities = [
        StoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HeraDatabase : RoomDatabase() {
    abstract fun heraDao(): HeraDao
}