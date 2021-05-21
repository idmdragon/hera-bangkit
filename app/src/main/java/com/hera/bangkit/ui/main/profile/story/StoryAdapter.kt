package com.hera.bangkit.ui.main.profile.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.StoryItemBinding

class StoryAdapter(private val listStory : ArrayList<StoryEntity>): RecyclerView.Adapter<StoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val itemStoryBinding = StoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return StoryViewHolder(itemStoryBinding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = listStory[position]
        holder.bind(story)
    }

    override fun getItemCount() = listStory.size

}