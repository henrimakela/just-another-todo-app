package com.henri.yearlylist.data

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.data.room.ItemDao
import com.henri.yearlylist.data.room.ItemDatabase

class ItemRepository {

    private lateinit var itemDao: ItemDao
    private lateinit var allItems: LiveData<List<Item>>

    constructor(application: Application){
        var itemDatabase = ItemDatabase.getInstance(application)
        itemDao = itemDatabase!!.itemDao()
        allItems = itemDao.getAllItems()
    }

    fun insert(item: Item){
        InsertItemAsyncTask(itemDao).execute(item)
    }
    fun update(item: Item){
        UpdateItemAsyncTask(itemDao).execute(item)
    }
    fun delete(item: Item){
        DeleteItemAsyncTask(itemDao).execute(item)
    }
    fun getAllItems(): LiveData<List<Item>>{
        return allItems
    }

    private class InsertItemAsyncTask(itemDao: ItemDao) : AsyncTask<Item, Void, Void?>(){

        private lateinit var itemDao: ItemDao

        init {
            this.itemDao = itemDao
        }
        override fun doInBackground(vararg params: Item?): Void? {
            itemDao.insert(params[0]!!)
            return null
        }

    }

    private class UpdateItemAsyncTask(itemDao: ItemDao) : AsyncTask<Item, Void, Void?>(){

        private lateinit var itemDao: ItemDao

        init {
            this.itemDao = itemDao
        }
        override fun doInBackground(vararg params: Item?): Void? {
            itemDao.update(params[0]!!)
            return null
        }

    }
    private class DeleteItemAsyncTask(itemDao: ItemDao) : AsyncTask<Item, Void, Void?>(){

        private lateinit var itemDao: ItemDao

        init {
            this.itemDao = itemDao
        }
        override fun doInBackground(vararg params: Item?): Void? {
            itemDao.delete(params[0]!!)
            return null
        }

    }

}