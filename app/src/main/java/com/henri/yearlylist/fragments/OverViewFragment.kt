package com.henri.yearlylist.fragments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.henri.yearlylist.R
import com.henri.yearlylist.viewmodels.ItemViewModel
import kotlinx.android.synthetic.main.over_view_fragment.*

class OverViewFragment : Fragment() {

    companion object {
        fun newInstance() = OverViewFragment()
    }

    private lateinit var viewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.over_view_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ItemViewModel::class.java)
        bindUI()
    }

    private fun bindUI() {
        imageview_hobby.setOnClickListener { swapToCenter(imageview_hobby) }
        imageview_other.setOnClickListener { swapToCenter(imageview_other) }
        imageview_work.setOnClickListener { swapToCenter(imageview_work) }
    }

    fun swapToCenter(view: View) {

        overview_container.visibility = View.GONE
        TransitionManager.beginDelayedTransition(overview_layout)
        overview_placeholder.setContentId(view.id)

        when (view.id) {
            R.id.imageview_hobby -> {
                overview_title.text = "Items regarding hobbies"
                overview_completed_percentage.text = viewModel.getCompletedPercentage(2)

            }
            R.id.imageview_other -> {
                overview_title.text = "Items regarding other things"
                overview_completed_percentage.text = viewModel.getCompletedPercentage(3)

            }
            R.id.imageview_work -> {
                overview_title.text = "Items regarding work"
                overview_completed_percentage.text = viewModel.getCompletedPercentage(1)

            }
        }
        overview_container.visibility = View.VISIBLE
    }
}
