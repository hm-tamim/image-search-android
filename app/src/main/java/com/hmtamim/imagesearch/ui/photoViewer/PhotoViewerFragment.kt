package com.hmtamim.imagesearch.ui.photoViewer

import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentHomeBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoViewerFragment : BaseFragment<FragmentHomeBinding, PhotoViewerViewModel>(
    PhotoViewerViewModel::class.java,
    R.layout.fragment_home
) {
    override fun initViews() {

    }

    override fun liveEventsObservers() {

    }

    override fun clickListeners() {

    }

}