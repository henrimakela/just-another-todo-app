package com.henri.yearlylist.recyclerview

import com.henri.yearlylist.data.room.Item

interface ListItemClickListener {
    fun handleClick(item: Item)
}