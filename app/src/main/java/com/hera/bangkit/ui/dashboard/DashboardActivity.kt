package com.hera.bangkit.ui.dashboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.databinding.ActivityDashboardBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDashboardMulaiSekarang.setOnClickListener {
            startActivity(Intent(this@DashboardActivity, RegisterActivity::class.java))
        }
    }
}