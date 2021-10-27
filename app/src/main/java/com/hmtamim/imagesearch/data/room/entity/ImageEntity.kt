package com.hmtamim.imagesearch.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "images_table")
data class ImageEntity(

    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("query")
    var query: String = "",

    @SerializedName("webformatURL")
    val webformatURL: String,

    @SerializedName("previewURL")
    val previewURL: String,

    @SerializedName("largeImageURL")
    val largeImageURL: String,

    @SerializedName("datetime")
    var dateTime: Long

)
