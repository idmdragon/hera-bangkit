package com.hera.bangkit.ui.main.post.sos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.databinding.ActivitySosBinding

class SosActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCancelSos.setOnClickListener {
            finish()
        }
    }
}