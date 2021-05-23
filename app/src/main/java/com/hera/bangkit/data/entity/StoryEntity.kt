package com.hera.bangkit.data.entity

data class StoryEntity(


        val category: String = "",
        val content: String ="",
        val imgContent: String ="",
        var like: Int = 0,
        @field:JvmField
        var isLike : Boolean = false,
        var location : String = "",
        val timeUpload: String ="",
        val userID: String = ""
) {
}