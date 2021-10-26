package com.hmtamim.imagesearch.ui.gallery

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentGalleryBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.gallery.controller.GalleryController
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
            controller.requestModelBuild()
        })

        viewModel.getGridSizeLiveData().observe(viewLifecycleOwner, Observer {
            layoutManager.spanCount = it
            binding.btnGrid.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    viewModel.getGridImageDrawable(it)
                )
            )
        })
    }

    override fun setupRecycler() {
        layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = controller.adapter
    }

    override fun clickListeners() {

    }

}