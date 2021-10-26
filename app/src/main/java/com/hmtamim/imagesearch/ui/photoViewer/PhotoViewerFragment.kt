package com.hmtamim.imagesearch.ui.photoViewer

import android.graphics.drawable.Drawable
import android.transition.TransitionInflater
import android.widget.ImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.FragmentPhotoViewerBinding
import com.hmtamim.imagesearch.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoViewerFragment : BaseFragment<FragmentPhotoViewerBinding, PhotoViewerViewModel>(
    PhotoViewerViewModel::class.java,
    R.layout.fragment_photo_viewer
) {

    override fun initViews() {
        postponeEnterTransition()
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.shared_image)
    }

    override fun liveEventsObservers() {
        viewModel.getLivData().observe(this, Observer {
            binding.image.transitionName = it
            binding.image.loadWithTransitionCallback(it, true) {
                startPostponedEnterTransition()
            }
        })
    }

    override fun clickListeners() {

    }

    private fun ImageView.loadWithTransitionCallback(
        url: String,
        loadOnlyFromCache: Boolean = false,
        onLoadingFinished: () -> Unit = {}
    ) {

        val listener = object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadingFinished()
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                onLoadingFinished()
                return false
            }
        }

        val requestOptions = RequestOptions.placeholderOf(R.drawable.bg_placeholder)
            .dontTransform()
            .onlyRetrieveFromCache(loadOnlyFromCache)

        Glide.with(this)
            .load(url)
            .apply(requestOptions)
            .listener(listener)
            .into(this)
    }

}