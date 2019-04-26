package com.henri.yearlylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

class ItemRecyclerViewAdapter() : RecyclerView.Adapter<ItemViewHolder>() {
    var myItems: List<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        // set the view's size, margins, paddings and layout parameters

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(myItems[position])
    }
    internal fun setItems(itemList: List<Item>) {
        this.myItems = itemList
        notifyDataSetChanged()
    }
}