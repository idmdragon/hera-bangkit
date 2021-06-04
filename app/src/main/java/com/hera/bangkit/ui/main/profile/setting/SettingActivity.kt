package com.hera.bangkit.ui.main.profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivitySettingBinding
import com.hera.bangkit.ui.auth.login.LoginActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySettingBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnBack.setOnClickListener {
                finish()
            }

            btnLogout.setOnClickListener {
                firebaseAuth.signOut()
                startActivity(Intent(this@SettingActivity,LoginActivity::class.java)).also {
                    finish()
                }
            }

        }
    }
}