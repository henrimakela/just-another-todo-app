package com.henri.yearlylist

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.databinding.ListItemBinding

class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    @RequiresApi(Build.VERSION_CODES.M)
    fun bind(item: Item){
        when(item.priority){
            1 -> binding.root.setBackgroundColor(binding.root.context.getColor(R.color.work))
            2 -> binding.root.setBackgroundColor(binding.root.context.getColor(R.color.hobby))
            3 -> binding.root.setBackgroundColor(binding.root.context.getColor(R.color.other))
        }

        binding.item = item
    }
}