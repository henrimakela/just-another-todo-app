package com.henri.yearlylist.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.data.MockListData

class MyListViewModel : ViewModel() {
    val items: MutableLiveData<List<Item>> by lazy {
        MutableLiveData<List<Item>>()
    }

    init {
        items.postValue(MockListData.list)
    }
}
