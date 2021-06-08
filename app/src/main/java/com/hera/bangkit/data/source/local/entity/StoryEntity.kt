package com.hera.bangkit.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "story_list")
data class StoryEntity(
        val avatarProfile : String = "",
        val category: String = "",
        @PrimaryKey
        val content: String ="",
        val imgContent: String ="",
        @field:JvmField
        var isLike : Boolean = false,
        @field:JvmField
        var isUpvoted : Boolean = false,
        var like: Int = 0,
        val timeUpload: String ="",
        val userName : String = "",
        var upvote : Int = 0,
) {
}