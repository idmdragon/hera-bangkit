package com.hera.bangkit.ui.main.hastag
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.hera.bangkit.data.source.remote.response.HastagEntity
import com.hera.bangkit.databinding.HastagItemBinding

class HastagViewHolder(private val binding: HastagItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(hastag : HastagEntity){
         with(binding){
             tvCategory.text = hastag.Category
             tvCountPost.text = hastag.Count.toString()
             itemView.setOnClickListener {
                 val intent = Intent(itemView.context, HastagPageActivity::class.java)
                 intent.putExtra(HastagPageActivity.CATEGORY, hastag.Category)
                 itemView.context.startActivity(intent)
             }
         }
    }
}