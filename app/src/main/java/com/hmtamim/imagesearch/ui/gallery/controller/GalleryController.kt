package com.hmtamim.imagesearch.ui.gallery.controller

import com.airbnb.epoxy.EpoxyController
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.ui.gallery.model.EmptyModel_
import com.hmtamim.imagesearch.ui.gallery.model.LoadingModel_
import com.hmtamim.imagesearch.ui.gallery.model.PhotoThumbModel_

class GalleryController(val clickListener: ClickListener) : EpoxyController() {

    var isLoading = true
    var list: List<ImageEntity> = ArrayList()

    init {
        setFilterDuplicates(true)
    }

    override fun buildModels() {

        list.forEach {
            PhotoThumbModel_()
                .id(it.id)
                .model(it)
                .clickListener { model, parentView, clickedView, position ->
                    model.model()?.let { it1 -> clickListener.onImageClick(it1) }
                }
                .addTo(this)
        }

        for (i in 1..28)
            PhotoThumbModel_()
                .id("skeleton", i.toString())
                .model(null)
                .addIf(list.isEmpty() && isLoading, this)

        LoadingModel_()
            .id("loading_model")
            .fullHeight(list.isEmpty())
            .spanSizeOverride { totalSpanCount, position, itemCount -> spanCount }
            .addIf(isLoading && list.isNotEmpty(), this)

        EmptyModel_()
            .id("empty_model")
            .addIf(!isLoading && list.isEmpty(), this)

    }

    interface ClickListener {
        fun onImageClick(model: ImageEntity)
    }
}