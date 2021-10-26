package com.hmtamim.imagesearch.data.repository

import com.hmtamim.imagesearch.BuildConfig
import com.hmtamim.imagesearch.data.remote.ApiClient
import com.hmtamim.imagesearch.model.PhotosResponse
import com.hmtamim.imagesearch.utils.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiRepository(var apiClient: ApiClient) {

    /** repository for all APIs*/

    fun getImages(query: String, page: Int, listener: ResponseListener<PhotosResponse>) {
        apiClient.getImages(BuildConfig.API_KEY, "photo", "popular", "40", query, page).enqueue(object : Callback<PhotosResponse> {
            override fun onResponse(
                call: Call<PhotosResponse>,
                response: Response<PhotosResponse>
            ) {
                response.body()?.let {
                    listener.onSuccess(it, response.code())
                } ?: run {
                    listener.onError("", response.code())
                }
            }

            override fun onFailure(call: Call<PhotosResponse>, t: Throwable) {
                listener.onError("Unknown error", 0)
                t.stackTrace
            }
        })
    }

}