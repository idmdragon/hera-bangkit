package com.hera.bangkit.data.response

data class StoryResponse(

        val category: String = "",
        val content: String ="",
        val imgContent: String ="",
        @field:JvmField
        var isLike : Boolean = false,
        var like: Int = 0,
        var location : String = "",
        val timeUpload: String ="",
        val userID: String = ""
) {
}