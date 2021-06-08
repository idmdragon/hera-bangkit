package com.hera.bangkit.ui.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hera.bangkit.R
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.databinding.StoryItemBinding
import com.hera.bangkit.ui.viewmodel.StoryViewModel
import com.hera.bangkit.utils.DateHelper
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeAdapter @Inject constructor(private val viewModel : StoryViewModel, private val listStory: ArrayList<StoryEntity>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {


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
                tvTimeUpload.text = displayDate(story.timeUpload)
                tvCategory.text = story.category
                tvContent.text = story.content


                Glide.with(itemView.context)
                    .load(story.avatarProfile)
                    .apply(RequestOptions().override(50, 50))
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

                if (story.isUpvoted == false) {
                    btnUp.setImageResource(
                        R.drawable.ic_up_inactive
                    )
                }else{
                    btnUp.setImageResource(
                        R.drawable.ic_up_active
                    )
                }

                if (story.isLike == false) {
                    btnLove.setImageResource(
                        R.drawable.ic_love_home
                    )
                }else{
                    btnLove.setImageResource(
                        R.drawable.ic_love_active
                    )
                }


                btnUp.setOnClickListener {

                    if (story.isUpvoted == false) {
                        story.isUpvoted = true
                        story.upvote += 1
                        btnUp.setImageResource(
                            R.drawable.ic_up_active
                        )
                        viewModel.increaseUpvote(story)
                    } else {
                        story.isUpvoted = false
                        btnUp.setImageResource(
                            R.drawable.ic_up_inactive
                        )
                        story.upvote -= 1

                        viewModel.decreaseUpvote(story)
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
                        viewModel.increaseStory(story)
                    } else {
                        story.isLike = false
                        btnLove.setImageResource(
                            R.drawable.ic_love_home
                        )
                        story.like -= 1
                        viewModel.decreaseStory(story)
                    }

                    tvLoveNumCount.text = story.like.toString()
                }

                btnOption.setOnClickListener {
                    showMenu(it, R.menu.option_menu,itemView.context)
                }
            }

        }

    }

    private fun showMenu(it: View, optionMenu: Int, context: Context) {
        val popup = PopupMenu(context,it )
        popup.menuInflater.inflate(optionMenu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem: MenuItem ->

        if(menuItem.itemId == R.id.report){
            Toast.makeText(context,"Cerita telah di Laporkan",Toast.LENGTH_LONG).show()
        }
        true
        }
        popup.setOnDismissListener {
            // Respond to popup being dismissed.
        }
        // Show the popup menu.
        popup.show()
    }

    @SuppressLint("SimpleDateFormat")
    private fun displayDate(timeUpload: String):String{

        val dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val dt: Date = dateFormat.parse(timeUpload)!!

        val tdf: DateFormat = SimpleDateFormat("HH:mm")
        val dfmt: DateFormat = SimpleDateFormat("dd MMM yyyy")

        val timeOnly: String = tdf.format(dt)
        val dateOnly: String = dfmt.format(dt)

        return if(dateOnly == DateHelper.getDateOnly()){
            timeOnly
        }else{
            dateOnly
        }

    }
}