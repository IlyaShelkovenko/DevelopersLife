package com.gmail.hostov47.developerslife.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.gmail.hostov47.developerslife.R
import com.gmail.hostov47.developerslife.RootActivity
import com.gmail.hostov47.developerslife.extensions.dpToIntPx
import kotlinx.android.synthetic.main.fragment_over_view.*

class OverviewFragment : Fragment() {
    private lateinit var gifsPageAdapter: GifsPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gifsPageAdapter = GifsPageAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_over_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        view_pager.adapter = gifsPageAdapter
        tabs.setupWithViewPager(view_pager)
    }

    private fun setupToolbar() {
        (requireActivity() as RootActivity).setSupportActionBar(toolbar)
        val logo = if(toolbar.childCount == 2)
            toolbar.getChildAt(1) as? ImageView
        else null
        logo?.scaleType = ImageView.ScaleType.CENTER_CROP
        val lp = logo?.layoutParams as Toolbar.LayoutParams
        lp?.let {
            it.width = this.dpToIntPx(40)
            it.height = this.dpToIntPx(40)
            it.marginEnd = this.dpToIntPx(16)
            logo.layoutParams = it
        }
    }

}
