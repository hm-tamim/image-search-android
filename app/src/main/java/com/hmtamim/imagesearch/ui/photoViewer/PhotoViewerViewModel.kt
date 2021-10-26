package com.hmtamim.imagesearch.ui.photoViewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide.init
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PhotoViewerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val liveData = MutableLiveData<String>()

    init {
        liveData.value = savedStateHandle.get("image_url")
    }

    fun getLivData(): LiveData<String> = liveData

}