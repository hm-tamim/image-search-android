package com.hmtamim.imagesearch.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


abstract class BaseFragment<DATA_BINDING : ViewDataBinding, VIEW_MODEL : ViewModel>(
    private val viewModelClassType: Class<VIEW_MODEL>,
    @LayoutRes private val layout: Int
) : Fragment() {

    protected val viewModel: VIEW_MODEL by lazy {
        ViewModelProvider(this).get(viewModelClassType)
    }
    protected lateinit var binding: DATA_BINDING
    protected var bundle: Bundle? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        bundle = arguments
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        clickListeners()
        setupRecycler()
        liveEventsObservers()
    }

    protected abstract fun initViews()
    protected abstract fun clickListeners()
    protected abstract fun liveEventsObservers()

    protected open fun setupRecycler() {

    }

}