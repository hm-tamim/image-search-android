package com.hmtamim.imagesearch.data.remote

import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {

    @GET("image")
    fun getImages(): Call<List<ImageEntity>>

}