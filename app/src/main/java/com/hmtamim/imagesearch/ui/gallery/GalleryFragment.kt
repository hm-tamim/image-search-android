package com.hmtamim.imagesearch.ui.gallery

import androidx.lifecycle.Observer
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentGalleryBinding
import com.hmtamim.imagesearch.databinding.FragmentHomeBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.gallery.controller.GalleryController
import com.hmtamim.imagesearch.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>(
    GalleryViewModel::class.java,
    R.layout.fragment_gallery
) {

    private val controller: GalleryController by lazy {
        GalleryController()
    }

    override fun initViews() {

    }

    override fun liveEventsObservers() {
        viewModel.getPhotosLiveData().observe(viewLifecycleOwner, Observer {
            controller.list = it
            controller.requestModelBuild()
        })
    }

    override fun setupRecycler() {
        binding.recyclerView.adapter = controller.adapter
    }

    override fun clickListeners() {

    }

}