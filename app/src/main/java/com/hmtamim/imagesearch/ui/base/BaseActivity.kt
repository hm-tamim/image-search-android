package com.hmtamim.imagesearch.ui.base

import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/** Base activity for auto data binding, reduce boilerplate and hide keyboard events*/
abstract class BaseActivity<DATA_BINDING : ViewDataBinding, VIEW_MODEL : ViewModel>(
    private val viewModelClassType: Class<VIEW_MODEL>,
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

    protected lateinit var viewModel: VIEW_MODEL
    protected lateinit var binding: DATA_BINDING

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this).get(viewModelClassType)
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        initViews()
        clickListeners()
        liveEventsObservers()
    }

    // hide keyboard on touch outside
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (currentFocus != null) {
            val imm = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    protected abstract fun initViews()
    protected abstract fun liveEventsObservers()
    protected abstract fun clickListeners()
}

