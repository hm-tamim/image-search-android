package com.hmtamim.imagesearch.ui.gallery.controller

import com.airbnb.epoxy.EpoxyController
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.ui.gallery.model.PhotoThumbModel_

class GalleryController : EpoxyController() {

    var list: List<ImageEntity> = ArrayList()

    override fun buildModels() {
        list.forEach {
            PhotoThumbModel_()
                .id(it.id)
                .model(it)
                .addTo(this)
        }
    }
}