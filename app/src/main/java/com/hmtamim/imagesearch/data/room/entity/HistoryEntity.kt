package com.hmtamim.imagesearch.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "histories")
data class HistoryEntity(
    @PrimaryKey val url: String,
    @ColumnInfo(name = "date") val date: Long,
    @ColumnInfo(name = "image") val image: String
) : Serializable
