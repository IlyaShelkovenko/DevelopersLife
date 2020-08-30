/**
 * Created by Ilia Shelkovenko on 29.08.2020.
 */

package com.gmail.hostov47.developerslife.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gmail.hostov47.developerslife.R
import com.gmail.hostov47.developerslife.databinding.FragmentGifCategoryBinding
import kotlinx.android.synthetic.main.fragment_gif_category.*

class GifCategoryFragment : Fragment()  {
    private lateinit var viewModel: GifCategoryViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gifCategory = arguments?.getString(GIF_CATEGORY) ?: GifsType.LATEST.title
        val vmFactory = GifCategoryViewModelFactory(gifCategory)
        viewModel = ViewModelProviders.of(this,vmFactory).get(GifCategoryViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentGifCategoryBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.currImageUrl.observe(viewLifecycleOwner, Observer {
            checkButtons()
            if(it.isNotEmpty())
                content_description.text = viewModel.sortedListFromDb.value?.get(viewModel.currImageIndex.value!!)?.description
        })

        btn_repeat_download.setOnClickListener {
            viewModel.handleRepeatDownLoadClick()
        }

        checkButtons()
        imBtn_forward.setOnClickListener {
            viewModel.handleForwardClick()
            checkButtons()
        }
        imBtn_back.setOnClickListener {
            viewModel.handleBackClick()
            checkButtons()
        }
    }


    private fun checkButtons() {
        if(viewModel.currImageIndex.value == (viewModel.listOfGifs.value?.size)?.minus(1)){
            imBtn_forward.isEnabled = false
            imBtn_forward.background = resources.getDrawable(R.drawable.btn_bg_circle_dark)
        }else{
            imBtn_forward.isEnabled = true
            imBtn_forward.background = resources.getDrawable(R.drawable.btn_bg_circle)
        }
        if(viewModel.currImageIndex.value == 0){
            imBtn_back.isEnabled = false
            imBtn_back.background = resources.getDrawable(R.drawable.btn_bg_circle_dark)
        }else {
            imBtn_back.isEnabled = true
            imBtn_back.background = resources.getDrawable(R.drawable.btn_bg_circle)
        }
        if(viewModel.listOfGifs.value?.size == null || viewModel.listOfGifs.value?.size == 0){
            imBtn_forward.isEnabled = false
            imBtn_forward.background = resources.getDrawable(R.drawable.btn_bg_circle_dark)
            imBtn_back.isEnabled = false
            imBtn_back.background = resources.getDrawable(R.drawable.btn_bg_circle_dark)
        }

    }

    companion object {
        private const val GIF_CATEGORY = "gif_category"

        fun newInstance(gifCategory : String) : GifCategoryFragment {
            return GifCategoryFragment().apply {
                arguments = bundleOf(GIF_CATEGORY to gifCategory)
            }
        }
    }
}