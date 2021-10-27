package com.hmtamim.imagesearch.ui.photoViewer.model

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.davemorrissey.labs.subscaleview.ImageSource
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.ItemImagePreviewBinding

@EpoxyModelClass(layout = R.layout.item_image_preview)
abstract class ImagePreviewModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var imgUrl: String = ""

    @EpoxyAttribute
    var onLoadingFinished: () -> Unit = {}

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        val binding = holder.dataBinding as ItemImagePreviewBinding

        binding.image.transitionName = imgUrl

        val requestOptions = RequestOptions.placeholderOf(R.drawable.bg_placeholder)
            .dontTransform()
            .onlyRetrieveFromCache(true)

        Glide.with(binding.image)
            .asBitmap()
            .apply(requestOptions)
            .load(imgUrl)
            .into(object : CustomTarget<Bitmap?>() {
                override fun onLoadCleared(placeholder: Drawable?) {}
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {
                    onLoadingFinished()
                    if (binding.image.context != null)
                        binding.image.setImage(ImageSource.bitmap(resource))
                }
            })
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {

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