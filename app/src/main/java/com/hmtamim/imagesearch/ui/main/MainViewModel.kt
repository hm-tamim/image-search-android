package com.hmtamim.imagesearch.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmtamim.imagesearch.data.repository.AppRepository
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val appRepository: AppRepository
) : ViewModel() {

    val photosLiveList = MutableLiveData<List<ImageEntity>>()

}