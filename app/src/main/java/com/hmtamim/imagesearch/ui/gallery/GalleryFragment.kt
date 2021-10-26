package com.hmtamim.imagesearch.ui.gallery

import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentHomeBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentHomeBinding, GalleryViewModel>(
    GalleryViewModel::class.java,
    R.layout.fragment_home
) {
    override fun initViews() {

    }

    override fun liveEventsObservers() {

    }

    override fun clickListeners() {

    }

}