package com.henri.yearlylist.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.henri.yearlylist.ItemRecyclerViewAdapter

import com.henri.yearlylist.R
import com.henri.yearlylist.databinding.MyListFragmentBinding
import com.henri.yearlylist.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.my_list_fragment.*

class MyListFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel

    companion object {
        fun newInstance() = MyListFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var binding : MyListFragmentBinding = DataBindingUtil.inflate(inflater ,R.layout.my_list_fragment,container , false)
        var view : View  = binding.root
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return view
    }

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        val adapter = ItemRecyclerViewAdapter(context!!)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        list_recyclerview.adapter = adapter
        list_recyclerview.layoutManager = LinearLayoutManager(context)

        viewModel.getAllItems().observe(this, Observer {
            adapter.setItems(it)
        })
    }

}
