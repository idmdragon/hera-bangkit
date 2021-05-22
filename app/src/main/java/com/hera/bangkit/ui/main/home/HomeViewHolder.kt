package com.hera.bangkit.ui.main.home

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.StoryItemBinding

class HomeViewHolder(private val binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(story: StoryEntity) {
        with(binding) {

//            tvUsername.text = story.Username
            tvTimeUpload.text = story.TimeUpload
            tvCategory.text = story.Category
            tvContent.text = story.Content


//            Glide.with(itemView.context)
//                .load(story.AvatarProfile)
//                    .into(ivAvatar)

            if (story.ImgContent.isEmpty()) {
                ivContent.isVisible = false
            } else {
                Glide.with(itemView.context)
                        .load(story.ImgContent)
                        .into(ivContent)
            }

            tvLoveNumCount.text = story.Like.toString()
/*

            btnLove.setOnClickListener {

                if (story.isLike == false) {
                    story.isLike = true
                    story.Like+=1
                    btnLove.setImageResource(
                            R.drawable.ic_love_active
                    )
                } else {
                    story.isLike = false
                    btnLove.setImageResource(
                            R.drawable.ic_love_home
                    )
                    story.Like-=1
                }
                tvLoveNumCount.text = story.Like.toString()

            }
 */

        }

    }

}