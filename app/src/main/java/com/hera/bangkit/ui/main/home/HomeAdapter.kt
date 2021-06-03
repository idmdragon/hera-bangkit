package com.hera.bangkit.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.StoryItemBinding

class HomeAdapter(private val listStory: ArrayList<StoryEntity>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    var onItemClick: ((StoryEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemStoryBinding =
            StoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(itemStoryBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val story = listStory[position]
        holder.bind(story)
    }

    override fun getItemCount() = listStory.size

    inner class HomeViewHolder(private val binding: StoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(story: StoryEntity) {
            with(binding) {

                tvUsername.text = story.userName
                tvTimeUpload.text = story.timeUpload
                tvCategory.text = story.category
                tvContent.text = story.content


                Glide.with(itemView.context)
                    .load(story.avatarProfile)
                    .apply(RequestOptions().override(54, 54))
                    .into(ivAvatar)

                if (story.imgContent.isEmpty()) {
                    ivContent.isVisible = false
                } else {
                    Glide.with(itemView.context)
                        .load(story.imgContent)
                        .into(ivContent)
                }

                tvLoveNumCount.text = story.like.toString()
                tvUpCount.text = story.upvote.toString()

                btnUp.setOnClickListener {

                    if (story.isUpvoted == false) {
                        story.isUpvoted = true
                        story.upvote += 1
                        btnUp.setImageResource(
                            R.drawable.ic_up_active
                        )
                    } else {
                        story.isUpvoted = false
                        btnUp.setImageResource(
                            R.drawable.ic_up_inactive
                        )
                        story.upvote -= 1
                    }
                    tvUpCount.text = story.upvote.toString()
                }


                btnLove.setOnClickListener {
                    if (story.isLike == false) {
                        story.isLike = true
                        story.like += 1
                        btnLove.setImageResource(
                            R.drawable.ic_love_active
                        )
                    } else {
                        story.isLike = false
                        btnLove.setImageResource(
                            R.drawable.ic_love_home
                        )
                        story.like -= 1
                    }
                    tvLoveNumCount.text = story.like.toString()
                }
            }

        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listStory[adapterPosition])
            }

        }
    }
}