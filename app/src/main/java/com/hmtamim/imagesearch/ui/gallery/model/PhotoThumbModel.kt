package com.hmtamim.imagesearch.ui.gallery.model

import android.view.View
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.databinding.ItemPhotoThumbBinding

@EpoxyModelClass(layout = R.layout.item_photo_thumb)
abstract class PhotoThumbModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var model: ImageEntity? = null

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    lateinit var clickListener: View.OnClickListener

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        val binding = holder.dataBinding as ItemPhotoThumbBinding

        model?.let {
            Glide.with(binding.image)
                .asBitmap()
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(RequestOptions().override(400, 400))
                .load(it.webformatURL)
                .into(binding.image)

            binding.image.transitionName = it.webformatURL
            binding.image.setOnClickListener(clickListener)
        }

    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {

    }
}