package com.hmtamim.imagesearch.ui.gallery

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.transition.TransitionInflater
import android.view.View
import android.view.View.OnLayoutChangeListener
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView.OnEditorActionListener
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.databinding.FragmentGalleryBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import com.hmtamim.imagesearch.ui.gallery.controller.GalleryController
import com.hmtamim.imagesearch.ui.main.MainViewModel
import com.hmtamim.imagesearch.utils.PaginationScrollListener
import com.hmtamim.imagesearch.utils.ToastUtils
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class GalleryFragment : BaseFragment<FragmentGalleryBinding, GalleryViewModel>(
    GalleryViewModel::class.java,
    R.layout.fragment_gallery
), GalleryController.ClickListener {

    // shared activity ViewModel send the images in photo viewer fragment viewpager
    val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var layoutManager: GridLayoutManager
    private val controller: GalleryController by lazy {
        GalleryController(this)
    }

    override fun initViews() {
        initTransitions()
        checkArguments()
        initSearch()
    }

    private fun initTransitions() {

        exitTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.exit_gallery)

        setExitSharedElementCallback(object : androidx.core.app.SharedElementCallback() {
            override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
            ) {
                super.onMapSharedElements(names, sharedElements)
                val selectedViewHolder: RecyclerView.ViewHolder? =
                    binding.recyclerView.findViewHolderForAdapterPosition(mainViewModel.selectedImagePosition)
                if (selectedViewHolder != null) {
                    sharedElements[names[0]] = selectedViewHolder.itemView.findViewById(R.id.image)
                }
            }
        })

        postponeEnterTransition()

    }

    private fun checkArguments() {
        arguments?.let {
            if (it.containsKey("query"))
                binding.etSearch.setText(it["query"].toString())
        }
    }

    private fun initSearch() {
        binding.etSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.etSearch.text.toString()
                if (query.isEmpty()) {
                    ToastUtils.show("Please type something to search", context)
                    return@OnEditorActionListener false
                }
                viewModel.clearAll()
                viewModel.query = binding.etSearch.text.toString()
                viewModel.getPhotos()
                hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.btnClearSearch.visibility =
                    if (binding.etSearch.text.toString().isEmpty()) View.GONE else View.VISIBLE
            }

            override fun afterTextChanged(s: Editable?) {

            }
        })
    }

    fun hideKeyboard() {
        binding.etSearch.postDelayed(Runnable {
            val imm: InputMethodManager = requireContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(
                binding.etSearch.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }, 50)
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

        binding.recyclerView.addOnLayoutChangeListener(
            object : OnLayoutChangeListener {
                override fun onLayoutChange(
                    view: View,
                    left: Int,
                    top: Int,
                    right: Int,
                    bottom: Int,
                    oldLeft: Int,
                    oldTop: Int,
                    oldRight: Int,
                    oldBottom: Int
                ) {
                    binding.recyclerView.removeOnLayoutChangeListener(this)
                    val viewAtPosition =
                        layoutManager.findViewByPosition(mainViewModel.selectedImagePosition)
                    if (viewAtPosition == null
                        || layoutManager.isViewPartiallyVisible(viewAtPosition, false, true)
                    ) {
                        binding.recyclerView.post { layoutManager.scrollToPosition(mainViewModel.selectedImagePosition) }
                    }
                }
            })
    }

    override fun clickListeners() {
        binding.btnClearSearch.setOnClickListener {
            binding.etSearch.setText("")
            binding.etSearch.requestFocus()
        }
    }

    override fun onImageClick(model: ImageEntity, imageView: ImageView, position: Int) {
        mainViewModel.photosLiveList.value = viewModel.photosArrayList
        val extras = FragmentNavigatorExtras(
            imageView to imageView.transitionName,
        )
        val bundle = Bundle()
        bundle.putString("image_url", model.webformatURL)
        bundle.putInt("image_position", position)
        navController?.navigate(R.id.photoViewerFragment, bundle, null, extras)
    }

}