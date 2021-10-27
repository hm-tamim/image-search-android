package com.hmtamim.imagesearch.ui.main

import androidx.lifecycle.Observer
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.databinding.ActivityMainBinding
import com.hmtsoft.webcapture.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(
    MainViewModel::class.java,
    R.layout.activity_main
) {

    override fun initViews() {

    }

    override fun liveEventsObservers() {
        viewModel.networkConnectionObserver.observe(this, Observer {

        })
    }

    override fun clickListeners() {

    }

}