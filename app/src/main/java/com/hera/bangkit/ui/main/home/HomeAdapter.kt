package com.hera.bangkit.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.databinding.StoryItemBinding

class HomeAdapter(private val listStory : ArrayList<StoryEntity>): RecyclerView.Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemStoryBinding = StoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(itemStoryBinding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val story = listStory[position]
        holder.bind(story)
    }

    override fun getItemCount() = listStory.size

}