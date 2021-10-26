package com.hmtamim.imagesearch.data.remote

import com.hmtamim.imagesearch.model.PhotosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("api/")
    fun getImages(
        @Query("key") apiKey: String,
        @Query("image_type") imageType: String,
        @Query("order") order: String,
        @Query("per_page") limit: String,
        @Query("q") query: String,
        @Query("page") page: Int,

        ): Call<PhotosResponse>

}