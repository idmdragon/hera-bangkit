package com.hera.bangkit.ui.main.home

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.databinding.StoryItemBinding

class HomeViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(story: StoryEntity) {
        with(binding) {

            tvUsername.text = story.userName
            tvTimeUpload.text = story.timeUpload
            tvCategory.text = story.category
            tvContent.text = story.content


            Glide.with(itemView.context)
                .load(story.avatarProfile)
                    .into(ivAvatar)

            if (story.imgContent.isEmpty()) {
                ivContent.isVisible = false
            } else {
                Glide.with(itemView.context)
                        .load(story.imgContent)
                        .into(ivContent)
            }

            tvLoveNumCount.text = story.like.toString()

            btnLove.setOnClickListener {

                if (story.isLike == false) {
                    story.isLike = true
                    story.like+=1
                    btnLove.setImageResource(
                            R.drawable.ic_love_active
                    )
                } else {
                    story.isLike = false
                    btnLove.setImageResource(
                            R.drawable.ic_love_home
                    )
                    story.like-=1
                }
                tvLoveNumCount.text = story.like.toString()

            }


        }

    }

}