package com.hera.bangkit.ui.main.post.report

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.viewModels
import com.hera.bangkit.R
import com.hera.bangkit.data.response.ReportEntity
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.ui.main.MainActivity
import com.hera.bangkit.utils.DateHelper
import com.hera.bangkit.utils.DummyUser
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostReportActivity : AppCompatActivity() {

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

                val category = etKategori.editText?.text.toString()
                val desc = etDesc.editText?.text.toString()

                if (category.isEmpty()){
                    kategoriMenu.error = "Pilih Kategori Terlebih dahulu"
                } else if (desc.isEmpty()){
                    etDesc.error= "Kronologi Tidak boleh kosong"
                }else{
                    startActivity(Intent(this@PostReportActivity,MainActivity::class.java)).also {
                        finish()
                    }
                    postData(desc,category)
                    Toast.makeText(this@PostReportActivity,"Berhasil diPOST",Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun postData(desc: String, category: String) {
        val dummyUser = DummyUser.generateUser()
        val reportItem = ReportEntity(
            dummyUser.address,
            category,
            desc,
            DummyUser.generateUser().Fullname,
            dummyUser.NIK,
            dummyUser.DateOfBirth,
            dummyUser.PhoneNumber,
            DateHelper.getCurrentDate()
        )


        viewModel.insertReport(reportItem)
    }
}