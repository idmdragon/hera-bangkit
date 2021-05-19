package com.hera.bangkit.ui.main.post.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityReportBinding

class ReportActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReportBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReportBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
        val items = listOf("Kekerasan Fisik", "Kejahatan Seksual", "Exploitasi Ekonomi", "Penculikan, Penjualan, atau Perdagangan", "Pornografi","Perlakuan Salah & Penelantaran","Anak yang Berkonflik dengan Hukum","Penyalahgunaan NAPZA")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        binding.kategoriMenu.setAdapter(adapter)
    }
}