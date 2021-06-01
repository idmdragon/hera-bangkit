package com.hera.bangkit.ui.main.profile.report

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.databinding.ReportListItemBinding

class ReportAdapter(private val listItems : ArrayList<ReportEntity>): RecyclerView.Adapter<ReportViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val reportItemBinding = ReportListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReportViewHolder(reportItemBinding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val item = listItems[position]
        holder.bind(item)
    }

    override fun getItemCount() = listItems.size

}