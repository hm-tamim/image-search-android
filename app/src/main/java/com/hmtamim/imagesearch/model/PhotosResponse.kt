package com.hmtamim.imagesearch.model

import com.google.gson.annotations.SerializedName
import com.hmtamim.imagesearch.data.room.entity.ImageEntity

data class PhotosResponse(
    @SerializedName("total")
    val total: Int,
    @SerializedName("totalHits")
    val totalHits: Int,
    @SerializedName("hits")
    val hits: List<ImageEntity>,
)
