package com.henri.yearlylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.activity_add_item.*

class AddItemActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

    }

    fun saveItem(view: View) {
        val item = Item(title_editbox.text.toString()
            , desc_editbox.text.toString()
            , priority_rdgb.findViewById<RadioButton>(priority_rdgb.checkedRadioButtonId).text.toString().toInt())

        viewModel.insert(item).also { Toast.makeText(this, getString(R.string.item_saved), Toast.LENGTH_LONG).show() }
    }
}
