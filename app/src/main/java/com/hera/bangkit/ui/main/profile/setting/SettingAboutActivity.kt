package com.hera.bangkit.ui.main.profile.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.databinding.ActivitySettingAboutBinding

class SettingAboutActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySettingAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener{
            finish()
        }
    }
}