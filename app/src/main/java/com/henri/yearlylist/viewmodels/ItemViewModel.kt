package com.henri.yearlylist.viewmodels

import android.app.Application
import androidx.databinding.Bindable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.henri.yearlylist.data.ItemRepository
import com.henri.yearlylist.data.room.Item

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private var itemRepository: ItemRepository = ItemRepository(application)
    private lateinit var allItems: LiveData<List<Item>>

   init{
       allItems = itemRepository.getAllItems()
    }

    fun getAllItems(): LiveData<List<Item>>{
        return allItems
    }

    fun getTestString(): String{
        return "pöö"
    }
    fun insert(item: Item){
        itemRepository.insert(item)
    }
    fun update(item: Item){
        itemRepository.update(item)
    }
    fun delete(item: Item){
        itemRepository.delete(item)
    }

}