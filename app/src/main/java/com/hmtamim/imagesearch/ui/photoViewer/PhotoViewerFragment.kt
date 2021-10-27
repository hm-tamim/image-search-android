package com.hmtamim.imagesearch.ui.photoViewer

import android.transition.TransitionInflater
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentPhotoViewerBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.main.MainViewModel
import com.hmtamim.imagesearch.ui.photoViewer.controller.ImageViewPagerController
import com.hmtamim.imagesearch.utils.ToastUtils
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
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
    }

    override fun setupRecycler() {
//        controller.addModelBuildListener {
//            startPostponedEnterTransition()
//        }
        binding.viewPager.adapter = controller.adapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    override fun liveEventsObservers() {

        mainViewModel.photosLiveList.observe(viewLifecycleOwner, Observer {
            controller.list = it
            controller.requestModelBuild()
            binding.viewPager.setCurrentItem(selectedPosition, false)
            startPostponedEnterTransition()
        })

//        viewModel.getLivData().observe(this, Observer {
//            binding.image.transitionName = it
//            binding.image.loadWithTransitionCallback(it, true) {
//                startPostponedEnterTransition()
//            }
//        })
    }

    override fun clickListeners() {
        binding.back.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    override fun startTransition() {
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