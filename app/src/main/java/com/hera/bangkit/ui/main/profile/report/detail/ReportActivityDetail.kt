package com.hera.bangkit.ui.main.profile.report.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
            tvCategory.text = item.Kategori
            tvKronologi.text = item.Kronologi
            tvLocation.text = item.Alamat
            tvName.text = item.Nama
            tvNik.text = item.NIK
            tvNoTelp.text = item.NoTlp
            tvTglLahir.text = item.TTL
            tvTimeUpload.text = item.TanggalLapor
        }
    }
}