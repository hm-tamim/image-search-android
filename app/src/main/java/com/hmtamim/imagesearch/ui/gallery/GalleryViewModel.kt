package com.hmtamim.imagesearch.ui.gallery

import androidx.lifecycle.*
import com.hmtamim.imagesearch.R
import com.hmtamim.imagesearch.data.repository.ApiRepository
import com.hmtamim.imagesearch.data.repository.AppRepository
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.model.PhotosResponse
import com.hmtamim.imagesearch.utils.ResponseListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private var apiRepository: ApiRepository,
    private var appRepository: AppRepository,
    private var savedStateHandle: SavedStateHandle
) : ViewModel() {

    var query = "nature"
    private var page = 1
    private var shouldFetchNextPage = true
    private var gridSizeLiveData: MutableLiveData<Int> = MutableLiveData()
    private val photosLiveList: MutableLiveData<List<ImageEntity>> = MutableLiveData()
    private val photosArrayList: MutableList<ImageEntity> = ArrayList()

    init {
        page = 1
        gridSizeLiveData.value = 2
        if (savedStateHandle.contains("query")) {
            query = savedStateHandle.get<String>("query").toString()
        }
        getPhotos()
    }

    fun getPhotos() {

        if (!shouldFetchNextPage) {
            photosLiveList.value = photosArrayList
            return
        }

        apiRepository.getImages(query, page, object : ResponseListener<PhotosResponse> {
            override fun onSuccess(response: PhotosResponse, statusCode: Int) {
                insertPhotosToDatabase(response.hits)
                photosArrayList.addAll(response.hits)
                photosLiveList.value = photosArrayList
                if (response.totalHits > photosArrayList.size)
                    page++
                else
                    shouldFetchNextPage = false
            }

            override fun onError(errorBody: String, errorCode: Int) {

            }
        })
    }

    fun insertPhotosToDatabase(list: List<ImageEntity>) {
        viewModelScope.launch {
            try {
                appRepository.insertAllPhotos(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getPhotosLiveData(): LiveData<List<ImageEntity>> {
        return photosLiveList
    }

    fun getGridSizeLiveData(): LiveData<Int> {
        return gridSizeLiveData
    }

    fun updateGridSize() {
        var currentGridSize = 2
        var nextGridSize = 3
        gridSizeLiveData.value?.let {
            currentGridSize = it
        }

        when (currentGridSize) {
            2 -> nextGridSize = 3
            3 -> nextGridSize = 4
            4 -> nextGridSize = 2
        }

        gridSizeLiveData.value = nextGridSize
    }

    fun getGridImageDrawable(currentGridSize: Int): Int {
        return when (currentGridSize) {
            2 -> R.drawable.ic_grid_3
            3 -> R.drawable.ic_grid_4
            4 -> R.drawable.ic_grid_2
            else -> R.drawable.ic_grid_2
        }
    }

    fun clearAll() {
        page = 1
        shouldFetchNextPage = true
        photosArrayList.clear()
    }

}