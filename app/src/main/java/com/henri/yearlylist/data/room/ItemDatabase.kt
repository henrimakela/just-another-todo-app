package com.henri.yearlylist.data.room

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.henri.yearlylist.data.MockListData

@Database(entities = [Item::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        private var INSTANCE: ItemDatabase? = null

        fun getInstance(context: Context): ItemDatabase {
            if (INSTANCE == null) {
                synchronized(ItemDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ItemDatabase::class.java, "items.db")
                        .fallbackToDestructiveMigration().build()
                        //.addCallback(CALLBACK)

                }
            }
            return INSTANCE!!
        }

        private val CALLBACK = object: RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(INSTANCE!!).execute()
            }
        }
        fun destroyInstance() {
            INSTANCE = null
        }


    }

    private class PopulateDbAsyncTask(itemDb: ItemDatabase) : AsyncTask<Void, Void, Void?>(){

        private var itemDao: ItemDao = itemDb.itemDao()

        override fun doInBackground(vararg params: Void?): Void? {
            itemDao.insert(Item("jöjöjöö", "asd", 2))
            itemDao.insert(Item("ölölölö", "ölö", 1))
            itemDao.insert(Item( "asdasd", "sd", 1))

            return null
        }


    }
}