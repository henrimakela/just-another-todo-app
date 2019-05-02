package com.henri.yearlylist

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.databinding.ListItemBinding

class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(item: Item){
        val card = binding.root as CardView
        when(item.priority){
            1 -> card.setCardBackgroundColor(binding.root.context.getColor(R.color.work))
            2 -> card.setCardBackgroundColor(binding.root.context.getColor(R.color.hobby))
            3 -> card.setCardBackgroundColor(binding.root.context.getColor(R.color.other))
        }
        binding.item = item
    }
}