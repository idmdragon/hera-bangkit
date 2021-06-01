package com.hera.bangkit.ui.main.profile.report.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.databinding.ActivityReportDetailBinding

class ReportActivityDetail : AppCompatActivity() {

    companion object{
        const val REPORT_PARCELABLE = "report_parcelable"
    }

    private lateinit var binding : ActivityReportDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        val reportIntent = intent.getParcelableExtra<ReportEntity>(REPORT_PARCELABLE)
        if (reportIntent != null) {
            setView(reportIntent)
        }
    }

    private fun setView(item: ReportEntity) {
        with(binding){
            tvCategory.text = item.category
            tvKronologi.text = item.description
            tvLocation.text = item.address
            tvName.text = item.fullname
            tvNik.text = item.nik
            tvNoTelp.text = item.phoneNumber
            tvTglLahir.text = item.ttl
            tvTimeUpload.text = item.uploadTime
        }
    }
}