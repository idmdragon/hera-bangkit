package com.hera.bangkit.ui.main.profile.story

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.databinding.StoryItemBinding

class StoryViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(story: StoryEntity) {
        with(binding){

            tvUsername.text = story.userName
            tvTimeUpload.text = story.timeUpload
            tvCategory.text = story.category
            tvContent.text = story.content
            tvLoveNumCount.text = story.like.toString()

            Glide.with(itemView.context)
                    .load(story.avatarProfile)
                    .into(ivAvatar)

            if (story.imgContent.isEmpty()){
                ivContent.isVisible = false
            }else{
                Glide.with(itemView.context)
                    .load(story.imgContent)
                    .into(ivContent)
            }


        }

    }
}