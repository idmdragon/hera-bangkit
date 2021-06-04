package com.hera.bangkit.ui.main.hastag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.ActivityHastagPageBinding
import com.hera.bangkit.ui.main.home.HomeAdapter
import com.hera.bangkit.ui.viewmodel.StoryViewModel

class HastagPageActivity : AppCompatActivity() {

    companion object{
        const val CATEGORY = "category"
    }
    private lateinit var adapter: HomeAdapter
    private val viewModel : StoryViewModel by viewModels()

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
        viewModel.getStoryWithTag(hastagCategory.toString()).observe(this,::setList)
    }

    private fun setList(items: ArrayList<StoryEntity>) {
        binding.rvHastag.layoutManager = LinearLayoutManager(this)
        adapter = HomeAdapter(viewModel,items)
        adapter.notifyDataSetChanged()
        binding.rvHastag.adapter = adapter
    }
}