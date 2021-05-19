package com.hera.bangkit.ui.main.post.story

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityReportBinding
import com.hera.bangkit.databinding.ActivityStoryBinding

class StoryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityStoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}