package com.henri.yearlylist.data.room

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "item_table")
data class Item(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var description: String,
    var priority: Int,
    var done: Boolean
) : Serializable {
    @Ignore
    constructor(title: String, description: String, priority: Int, done: Boolean = false) : this(
        0,
        title,
        description,
        priority,
        done
    )
}