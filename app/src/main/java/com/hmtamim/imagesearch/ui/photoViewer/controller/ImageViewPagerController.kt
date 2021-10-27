package com.hmtamim.imagesearch.ui.photoViewer.controller

import com.airbnb.epoxy.EpoxyController
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.ui.photoViewer.model.ImagePreviewModel_

class ImageViewPagerController(val listener: ControllerListener) : EpoxyController() {

    var list: List<ImageEntity> = ArrayList()

    override fun buildModels() {
        list.forEach {
            ImagePreviewModel_()
                .id(it.id)
                .imgUrl(it.webformatURL)
                .onLoadingFinished {
                    listener.startTransition()
                }
                .addTo(this)
        }
    }

    interface ControllerListener {
        fun startTransition()
    }

}