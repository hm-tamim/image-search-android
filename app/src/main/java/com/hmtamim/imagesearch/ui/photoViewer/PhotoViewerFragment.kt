package com.hmtamim.imagesearch.ui.photoViewer

import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentPhotoViewerBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.main.MainViewModel
import com.hmtamim.imagesearch.ui.photoViewer.controller.ImageViewPagerController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoViewerFragment : BaseFragment<FragmentPhotoViewerBinding, PhotoViewerViewModel>(
    PhotoViewerViewModel::class.java,
    R.layout.fragment_photo_viewer
), ImageViewPagerController.ControllerListener {

    // sharing photo list with a common shared activity ViewModel
    val mainViewModel: MainViewModel by activityViewModels()
    var selectedPosition = 0
    private val controller: ImageViewPagerController by lazy {
        ImageViewPagerController(this)
    }

    override fun initViews() {
        arguments?.let {
            if (it.containsKey("image_position"))
                selectedPosition = it.getInt("image_position")
        }
    }

    private fun initTransitions() {
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.shared_image)

        setEnterSharedElementCallback(object : androidx.core.app.SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                super.onMapSharedElements(names, sharedElements)
                val current = controller.adapter.boundViewHolders.getHolderForModel(
                    controller.adapter.getModelAtPosition(binding.viewPager.currentItem)
                )
                if (current != null) {
                    sharedElements[names[0]] = current.itemView.findViewById(R.id.image)
                }
            }
        })

        postponeEnterTransition()
    }

    override fun setupRecycler() {
        binding.viewPager.adapter = controller.adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mainViewModel.selectedImagePosition = position
                super.onPageSelected(position)
            }
        })

        initTransitions()
    }

    override fun liveEventsObservers() {
        mainViewModel.photosLiveList.observe(viewLifecycleOwner, Observer {list ->
            list?.let {
                controller.list = it
                controller.requestModelBuild()
                binding.viewPager.setCurrentItem(selectedPosition, false)
            }
        })
    }

    override fun clickListeners() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun startTransition() {
        // start transition when image is loaded
        startPostponedEnterTransition()
    }

    // clear photos list from shared ViewModel when photo previewer is closed
    override fun onDestroy() {
        super.onDestroy()
        mainViewModel.photosLiveList.value = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainViewModel.photosLiveList.value = null
    }

}