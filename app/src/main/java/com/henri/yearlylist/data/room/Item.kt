package com.henri.yearlylist.data.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val priority: Int
) {
    @Ignore
    constructor(title: String = "", description: String = "", priority: Int) : this(
        0,
        title,
        description,
        priority
    )
}