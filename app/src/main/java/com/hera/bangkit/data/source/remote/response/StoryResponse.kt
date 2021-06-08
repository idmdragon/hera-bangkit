package com.hera.bangkit.data.source.remote.response

data class StoryResponse(
        val category: String = "",
        val content: String ="",
        val imgContent: String ="",
        @field:JvmField
        var isLike : Boolean = false,
        @field:JvmField
        var isUpvoted : Boolean = false,
        var like: Int = 0,
        val timeUpload: String ="",
        val userID: String = "",
        val upvote : Int = 0,
) {
}