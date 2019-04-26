package com.henri.yearlylist

import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.databinding.ListItemBinding

class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
    fun bind(item: Item){
        binding.item = item
    }
}