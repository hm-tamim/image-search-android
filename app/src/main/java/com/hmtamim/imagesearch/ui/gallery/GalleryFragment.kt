package com.hmtamim.imagesearch.ui.gallery

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.databinding.FragmentGalleryBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.gallery.controller.GalleryController
import com.hmtamim.imagesearch.utils.PaginationScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>(
    GalleryViewModel::class.java,
    R.layout.fragment_gallery
), GalleryController.ClickListener {

    private lateinit var layoutManager: GridLayoutManager
    private val controller: GalleryController by lazy {
        GalleryController(this)
    }

    override fun initViews() {
        postponeEnterTransition()
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
        controller.addModelBuildListener {
            startPostponedEnterTransition()
        }
        controller.spanCount = 2
        layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.layoutManager = layoutManager
        layoutManager.spanSizeLookup = controller.spanSizeLookup
        binding.recyclerView.adapter = controller.adapter
        binding.recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItem() {
                if (controller.isLoading)
                    return
                controller.isLoading = true
                controller.requestModelBuild()
                viewModel.getPhotos()
            }
        })

        controller.requestModelBuild()
    }

    override fun clickListeners() {

    }

    override fun onImageClick(model: ImageEntity, imageView: ImageView) {
        val extras = FragmentNavigatorExtras(
            imageView to imageView.transitionName,
        )
        val bundle = Bundle()
        bundle.putString("image_url", model.webformatURL)
        navController?.navigate(R.id.photoViewerFragment, bundle, null, extras)
    }

}