package com.hmtamim.imagesearch.data.remote

import com.hmtamim.imagesearch.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("api/?key=24040111-b402dd200ec58e239a0e78dea&q=nature&image_type=photo&pretty=true&page=1&order=popular&image_type=photo&per_page=40")
    fun getImages(): Call<PhotosResponse>

}