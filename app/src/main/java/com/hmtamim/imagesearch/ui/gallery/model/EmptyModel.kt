package com.hmtamim.imagesearch.ui.gallery.model

import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.ItemEmptyBinding
import com.hmtamim.imagesearch.databinding.ItemLoadingBinding


@EpoxyModelClass(layout = R.layout.item_empty)
abstract class EmptyModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var title = "Photos not found"

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        val binding = holder.dataBinding as ItemEmptyBinding
        binding.title.text = title
    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {

    }
}