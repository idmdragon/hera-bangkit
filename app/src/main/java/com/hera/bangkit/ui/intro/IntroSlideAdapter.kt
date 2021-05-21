package com.hera.bangkit.ui.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.R
import com.hera.bangkit.databinding.ActivityIntroBinding

class IntroSlideAdapter (private val introSlide: List<IntroSlide>): RecyclerView.Adapter<IntroSlideAdapter.IntroSlideViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container,parent,false))
    }

    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        val slide = introSlide[position]
        holder.bind(slide)
    }

    override fun getItemCount(): Int = introSlide.size

    inner class IntroSlideViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private  val textTitle = view.findViewById<TextView>(R.id.tv_ilu_title)
        private  val textDescription = view.findViewById<TextView>(R.id.tv_ilu_desc)
        private  val imageIcon = view.findViewById<ImageView>(R.id.iv_ilu_intro)

        fun bind(introSlide: IntroSlide){
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.ilustration )
        }
    }
}