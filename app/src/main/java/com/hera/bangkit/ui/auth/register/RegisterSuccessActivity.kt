package com.hera.bangkit.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityRegisterSuccessBinding
import com.hera.bangkit.ui.main.MainActivity

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnRegisterSuccess.setOnClickListener {
            startActivity(Intent(this@RegisterSuccessActivity, MainActivity::class.java))
        }
    }
}