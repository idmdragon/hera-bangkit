package com.hera.bangkit.data.entity

data class StoryEntity(
        val UserID: String = "",
        val TimeUpload: String ="",
        val Category: String = "",
        val Content: String ="",
        val ImgContent: String ="",
        var Like: Int = 0,
        @field:JvmField
        var isLike : Boolean = false,
        var location : String = ""
) {
}