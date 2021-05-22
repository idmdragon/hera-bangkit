package com.hera.bangkit.ui.main.profile.story

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.StoryItemBinding

class StoryViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(story: StoryEntity) {
        with(binding){

//            tvUsername.text = story.Username
            tvTimeUpload.text = story.TimeUpload
            tvCategory.text = story.Category
            tvContent.text = story.Content
            tvLoveNumCount.text = story.Like.toString()

//            Glide.with(itemView.context)
//                    .load(story.AvatarProfile)
//                    .into(ivAvatar)

            if (story.ImgContent.isEmpty()){
                ivContent.isVisible = false
            }else{
                Glide.with(itemView.context)
                    .load(story.ImgContent)
                    .into(ivContent)
            }


        }

    }
}