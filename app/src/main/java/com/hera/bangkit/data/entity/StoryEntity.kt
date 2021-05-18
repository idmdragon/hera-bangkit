package com.hera.bangkit.data.entity

data class StoryEntity(
        val Id: Int,
        val Username: String,
        val AvatarProfile: String,
        val TimeUpload: String,
        val Category: String,
        val Content: String,
        val ImgContent: String,
        var Like: Int,
) {
}