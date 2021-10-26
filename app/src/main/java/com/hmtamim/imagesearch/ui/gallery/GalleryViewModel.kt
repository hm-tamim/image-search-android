package com.hmtamim.imagesearch.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hmtamim.imagesearch.data.repository.ApiRepository
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.model.PhotosResponse
import com.hmtamim.imagesearch.utils.ResponseListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    var apiRepository: ApiRepository
) : ViewModel() {

    private val photosLiveList: MutableLiveData<List<ImageEntity>> = MutableLiveData()

    init {
        getPhotos()
    }

    fun getPhotos() {
        apiRepository.getImages(object : ResponseListener<PhotosResponse> {
            override fun onSuccess(response: PhotosResponse, statusCode: Int) {
                photosLiveList.value = response.hits
            }

            override fun onError(errorBody: String, errorCode: Int) {

            }

        })
    }

    fun getPhotosLiveData(): LiveData<List<ImageEntity>> {
        return photosLiveList
    }

}