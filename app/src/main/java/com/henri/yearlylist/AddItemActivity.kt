package com.henri.yearlylist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.henri.yearlylist.data.room.Item
import com.henri.yearlylist.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.activity_add_item.*
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class AddItemActivity : AppCompatActivity() {

    private lateinit var viewModel: ItemViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)

        drop_area_hobbies.setOnDragListener(MyDragListener(this))
        drop_area_other.setOnDragListener(MyDragListener(this))
        drop_area_work.setOnDragListener(MyDragListener(this))
    }


    fun createItem(view: View) {
        var item = Item(title_editbox.text.toString(), desc_editbox.text.toString(), 0)
        viewModel.setPendingItem(item)
        draggable_item.visibility = View.VISIBLE
        draggable_item.setOnTouchListener(MyTouchListener())
    }

    private inner class MyTouchListener : OnTouchListener {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                val shadowBuilder = View.DragShadowBuilder(view)
                view.startDragAndDrop(null, shadowBuilder, view, 0)
                view.visibility = View.INVISIBLE
                return true
            }
           return false
        }
    }

    private inner class MyDragListener(context : Context) : View.OnDragListener {

        val hoverShape = ContextCompat.getDrawable(context, R.drawable.drop_area_ready)
        val normalShape = ContextCompat.getDrawable(context, R.drawable.drop_area_normal)

        override fun onDrag(v: View, event: DragEvent): Boolean {

            when (event.action) {
                DragEvent.ACTION_DRAG_STARTED -> {

                }
                DragEvent.ACTION_DRAG_ENTERED -> v.background = hoverShape
                DragEvent.ACTION_DRAG_EXITED -> v.background = normalShape
                DragEvent.ACTION_DROP -> {
                    var item = viewModel.getPendingItem()
                    when (v.id) {
                        R.id.drop_area_hobbies -> item.priority = 2
                        R.id.drop_area_other -> item.priority = 3
                        R.id.drop_area_work -> item.priority = 1
                    }
                    val view = event.localState as View
                    view.visibility = View.GONE
                    viewModel.insert(item)
                    v.background = normalShape
                }
                DragEvent.ACTION_DRAG_ENDED -> {
                }
                else -> {
                }
            }
            return true
        }
    }
}


