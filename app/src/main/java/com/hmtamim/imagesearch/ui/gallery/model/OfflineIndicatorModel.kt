package com.hmtamim.imagesearch.ui.gallery.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyModelClass
import com.hmtamim.imagesearch.R

@EpoxyModelClass(layout = R.layout.item_offline_indicator)
abstract class OfflineIndicatorModel : DataBindingEpoxyModel() {
    override fun setDataBindingVariables(binding: ViewDataBinding?) {
    }
}