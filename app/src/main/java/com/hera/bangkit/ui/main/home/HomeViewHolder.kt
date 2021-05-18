package com.hera.bangkit.ui.main.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.HomeItemBinding

class HomeViewHolder(private val binding: HomeItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(story: StoryEntity) {
        with(binding){

            tvUsername.text = story.Username
            tvTimeUpload.text = story.TimeUpload
            tvCategory.text = story.Category
            tvContent.text = story.Content
            tvLoveNumCount.text = story.Like.toString()

            Glide.with(itemView.context)
                    .load(story.AvatarProfile)
                    .apply(RequestOptions().override(57, 57))
                    .into(ivAvatar)

            Glide.with(itemView.context)
                    .load(story.ImgContent)
                    .apply(RequestOptions().override(57, 57))
                    .into(ivContent)


        }

    }
}