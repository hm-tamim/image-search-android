package com.hmtamim.imagesearch.data.repository

import com.hmtamim.imagesearch.data.remote.ApiClient
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import com.hmtamim.imagesearch.utils.ResponseListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ApiRepository() {

    @Inject
    lateinit var apiClient: ApiClient

    /** repository for all APIs*/

    fun getImages(listener: ResponseListener<List<ImageEntity>>) {
        apiClient.getImages().enqueue(object : Callback<List<ImageEntity>> {
            override fun onResponse(
                call: Call<List<ImageEntity>>,
                response: Response<List<ImageEntity>>
            ) {
                response.body()?.let {
                    listener.onSuccess(it, response.code())
                } ?: run {
                    listener.onError("", response.code())
                }
            }

            override fun onFailure(call: Call<List<ImageEntity>>, t: Throwable) {
                listener.onError("Unknown error", 0)
            }
        })
    }

}