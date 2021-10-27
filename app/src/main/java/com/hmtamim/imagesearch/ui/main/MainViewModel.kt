package com.hmtamim.imagesearch.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmtamim.imagesearch.data.repository.AppRepository
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.utils.NetworkConnectionObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class MainViewModel @Inject constructor(
    val appRepository: AppRepository,
) : ViewModel() {

    var selectedImagePosition = 0
    val photosLiveList = MutableLiveData<List<ImageEntity>>()

}