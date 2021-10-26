package com.hmtamim.imagesearch.ui.gallery.model

import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import com.airbnb.epoxy.DataBindingEpoxyModel
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.ItemLoadingBinding


@EpoxyModelClass(layout = R.layout.item_loading)
abstract class LoadingModel : DataBindingEpoxyModel() {

    @EpoxyAttribute
    var fullHeight = false

    override fun bind(holder: DataBindingHolder) {
        super.bind(holder)
        val binding = holder.dataBinding as ItemLoadingBinding
        val param = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            if (fullHeight)
                ViewGroup.LayoutParams.MATCH_PARENT
            else
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        binding.container.layoutParams = param

    }

    override fun setDataBindingVariables(binding: ViewDataBinding?) {

    }
}