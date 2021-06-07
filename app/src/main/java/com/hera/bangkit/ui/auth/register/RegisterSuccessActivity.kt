package com.hera.bangkit.ui.auth.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.data.response.UserResponse
import com.hera.bangkit.data.source.remote.RemoteResponse
import com.hera.bangkit.databinding.ActivityRegisterSuccessBinding
import com.hera.bangkit.ui.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

class RegisterSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterSuccessBinding

    companion object{
        const val USERNAME = "username"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val intentValue = intent.getStringExtra(USERNAME)
            tvPengguna.text = intentValue
            btnRegisterSuccess.setOnClickListener {
                startActivity(Intent(this@RegisterSuccessActivity, MainActivity::class.java))
            }
        }
    }

}