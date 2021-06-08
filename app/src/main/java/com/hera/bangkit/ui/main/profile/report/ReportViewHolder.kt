package com.hera.bangkit.ui.main.profile.report

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.data.source.local.entity.ReportEntity
import com.hera.bangkit.databinding.ReportListItemBinding
import com.hera.bangkit.ui.main.profile.report.detail.ReportActivityDetail

class ReportViewHolder(private val binding: ReportListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ReportEntity) {
        with(binding) {
            val reportName = "Laporan A.N. ${item.fullname}"
            val reportCategory = "Kategori : ${item.category}"
            val reportDate = "Waktu Upload : ${item.uploadTime}"
            tvReportName.text = reportName
            tvCategory.text = reportCategory
            tvTimeUpload.text = reportDate

            itemView.setOnClickListener {
                val intent = Intent(itemView.context,ReportActivityDetail::class.java)
                intent.putExtra(ReportActivityDetail.REPORT_PARCELABLE,item)
                itemView.context.startActivity(intent)
            }

        }

    }
}