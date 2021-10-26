package com.hmtamim.imagesearch.ui.gallery

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentGalleryBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.gallery.controller.GalleryController
import com.hmtamim.imagesearch.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>(
    GalleryViewModel::class.java,
    R.layout.fragment_gallery
) {

    private lateinit var layoutManager: GridLayoutManager
    private val controller: GalleryController by lazy {
        GalleryController()
    }

    override fun initViews() {

    }

    override fun liveEventsObservers() {
        viewModel.getPhotosLiveData().observe(viewLifecycleOwner, Observer {
            controller.list = it
            controller.isLoading = false
            controller.requestModelBuild()
        })

        viewModel.getGridSizeLiveData().observe(viewLifecycleOwner, Observer {
            layoutManager.spanCount = it
            controller.spanCount = it
            binding.btnGrid.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    viewModel.getGridImageDrawable(it)
                )
            )
        })
    }

    override fun setupRecycler() {
        controller.spanCount = 2
        layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.recyclerView.adapter = controller.adapter
        binding.recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItem() {
//                if (controller.isLoading)
//                    return
                controller.isLoading = true
                controller.requestModelBuild()
                viewModel.getPhotos()
            }
        })

        controller.requestModelBuild()
    }

    override fun clickListeners() {

    }

}