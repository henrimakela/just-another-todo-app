package com.henri.yearlylist.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.henri.yearlylist.AddItemActivity
import com.henri.yearlylist.ItemOffsetDecoration
import com.henri.yearlylist.ItemRecyclerViewAdapter

import com.henri.yearlylist.R
import com.henri.yearlylist.databinding.MyListFragmentBinding
import com.henri.yearlylist.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.my_list_fragment.*

class MyListFragment : Fragment() {

    private lateinit var viewModel: ItemViewModel
    private lateinit var adapter: ItemRecyclerViewAdapter
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
        adapter = ItemRecyclerViewAdapter(context!!)

        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        list_recyclerview.adapter = adapter
        list_recyclerview.layoutManager = GridLayoutManager(context, 2)
        list_recyclerview.addItemDecoration(ItemOffsetDecoration(this.context!!, R.dimen.item_offset))

        viewModel.getAllItems().observe(this, Observer {
            adapter.setItems(it)
        })

        new_item_btn.setOnClickListener {
            startAddItemActivity()
        }

        setRecyclerViewItemTouchListener()

    }

    private fun setRecyclerViewItemTouchListener() {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                showDeletionConfirmationDialog(position)

            }
        }

        //4
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(list_recyclerview)
    }

    private fun showDeletionConfirmationDialog(position: Int){
        AlertDialog
            .Builder(context)
            .setTitle(R.string.delete)
            .setMessage(getString(R.string.delete_dialog_message))
            .setPositiveButton(getString(R.string.yes)) { dialog, which ->
                viewModel.delete(position)
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, which ->
                dialog.dismiss()
                adapter.notifyItemChanged(position)}
            .create()
            .show()


    }

    private fun startAddItemActivity(){
        val intent = Intent(activity, AddItemActivity::class.java)
        startActivity(intent)
    }

}
