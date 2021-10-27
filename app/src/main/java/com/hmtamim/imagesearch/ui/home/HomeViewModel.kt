package com.hmtamim.imagesearch.ui.home

import androidx.lifecycle.ViewModel
import com.hmtamim.imagesearch.utils.NetworkConnectionObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class HomeViewModel @Inject constructor(
    @Named("network_connection_livedata")
    var networkConnectionObserver: NetworkConnectionObserver
) : ViewModel() {

}