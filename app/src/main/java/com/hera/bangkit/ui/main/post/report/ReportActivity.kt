package com.hera.bangkit.ui.main.post.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.ReportEntity
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.ui.main.MainActivity
import com.hera.bangkit.utils.DateHelper
import com.hera.bangkit.utils.DummyUser

class ReportActivity : AppCompatActivity() {

    private lateinit var binding : ActivityReportBinding
    private val viewModel : ReportViewModel by viewModels()

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
        //untuk mendapatkan tanggal DateHelper.getCurrentDate()

        with(binding){
            btnPost.setOnClickListener {
                if (kategoriMenu.text.toString().isEmpty()){
                    kategoriMenu.error = "Pilih Kategori Terlebih dahulu"
                }
                else if (tvKronologi.text.toString().isEmpty()){
                    tvKronologi.error= "Kronologi Tidak boleh kosong"
                }else{
                    startActivity(Intent(this@ReportActivity,MainActivity::class.java)).also {
                        finish()
                    }
                    postData()
                }
            }
        }

    }

    private fun postData() {
         with(binding){
             val dummyUser = DummyUser.generateUser()
             val reportItem = ReportEntity(
                 dummyUser.NIK,
                 dummyUser.Fullname,
                 dummyUser.DateOfBirth,
                 dummyUser.PhoneNumber,
                 dummyUser.Address,
                 kategoriMenu.text.toString(),
                 tvKronologi.text.toString(),
                 DateHelper.getCurrentDate()
             )

             viewModel.postReport(reportItem)
         }
    }
}