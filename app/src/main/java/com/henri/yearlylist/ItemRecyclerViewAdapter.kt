package com.henri.yearlylist

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.databinding.ListItemBinding


class ItemRecyclerViewAdapter(context: Context) : RecyclerView.Adapter<ItemViewHolder>(), ListItemClickListener {

    private var context =  context
    var myItems: List<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        // create a new view
        //ei tarvita nyt kun tehdään binding
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(myItems[position])
        holder.binding.itemClickListener = this

    }
    internal fun setItems(itemList: List<Item>) {
        this.myItems = itemList
        notifyDataSetChanged()
    }
    override fun handleClick(item: Item) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("item_id", item.id)
        context.startActivity(intent)
    }
}