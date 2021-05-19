package com.hera.bangkit.ui.main.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.data.entity.HastagEntity
import com.hera.bangkit.databinding.HastagItemBinding

class HastagAdapter(private val hastagItems : ArrayList<HastagEntity>) : RecyclerView.Adapter<HastagViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HastagViewHolder {
       val binding = HastagItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HastagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HastagViewHolder, position: Int) {
        val hastag = hastagItems[position]
        holder.bind(hastag)
    }

    override fun getItemCount() = hastagItems.size
}