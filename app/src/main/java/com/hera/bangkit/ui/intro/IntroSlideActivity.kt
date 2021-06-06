package com.hera.bangkit.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityIntroBinding
import com.hera.bangkit.ui.auth.register.RegisterActivity

class IntroSlideActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    private val introSlideAdapter = IntroSlideAdapter(
        listOf(
            IntroSlide(
                "Berikan Sinyal SOS",
                "Gunakan sinyal SOS untuk menyelamatkan dirimu",
                R.drawable.ilu_sos_intro
            ),
            IntroSlide(
                "Bagikan ceritamu",
                "Bagikan cerita yang kamu alami, informasikan kasus yang menimpamu",
                R.drawable.ilu_story_intro
            ),
            IntroSlide(
                "Lapor KPPPA",
                "Beritahukan kepada KPPPA tentang apa yang kamu alami",
                R.drawable.ilu_report_intro
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.introSliderViewPager.adapter = introSlideAdapter
        setupIndicator()
        setCurrentIndicator(0)
        binding.introSliderViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        binding.btnNext.setOnClickListener{
            if(binding.introSliderViewPager.currentItem + 1 < introSlideAdapter.itemCount)
            {
                binding.introSliderViewPager.currentItem+=1
            }else{
                Intent (applicationContext, RegisterActivity::class.java).also {
                    startActivity(it)
                    finish()
                }
            }
        }
    }

    private fun setupIndicator(){
        val indicators = arrayOfNulls<ImageView>(introSlideAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8,0,8,0)
        for (i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply{
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_incative
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.indicatorContainer.addView(indicators[i])
        }
    }

    private fun setCurrentIndicator(index : Int){
        val childCount = binding.indicatorContainer.childCount
        for (i in 0 until childCount){
            val imageView = binding.indicatorContainer[i] as ImageView
            if(i == index){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            }
            else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_incative
                    ))
            }
        }
    }
}