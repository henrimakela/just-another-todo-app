package com.henri.yearlylist

import com.henri.yearlylist.data.room.Item

interface ListItemClickListener {
    fun handleClick(item: Item)
}