package com.henri.yearlylist.data.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {

    @Insert
    fun insert(item: Item)

    @Update
    fun update(item: Item)

    @Delete
    fun delete(item: Item)

    @Query("SELECT * FROM item_table ORDER BY priority DESC")
    fun getAllItems(): LiveData<List<Item>>
}