package com.henri.yearlylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.henri.yearlylist.databinding.ActivityDetailBinding
import com.henri.yearlylist.viewmodels.ItemViewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var viewModel: ItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        bindUI()
    }

    fun bindUI(){
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        viewModel.getItemById(intent.extras.get("item_id") as Int).observe(this, Observer {
            binding.item = it
        })
    }
}
