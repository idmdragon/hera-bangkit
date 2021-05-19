package com.hera.bangkit.ui.main.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityHastagPageBinding

class HastagPageActivity : AppCompatActivity() {

    companion object{
        const val CATEGORY = "category"
    }

    private lateinit var binding : ActivityHastagPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHastagPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val hastagCategory = intent.getStringExtra(CATEGORY)

        with(binding){
            tvHastag.text = hastagCategory
            btnBack.setOnClickListener {
                finish()
            }
        }

    }
}