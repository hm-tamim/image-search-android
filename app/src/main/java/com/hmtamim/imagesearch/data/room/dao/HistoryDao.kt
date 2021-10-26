package com.hmtamim.imagesearch.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hmtamim.imagesearch.data.room.entity.ImageEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM images_table ORDER BY dateTime DESC")
    fun getAll(): LiveData<List<ImageEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: ImageEntity)

    @Delete
    suspend fun delete(entity: ImageEntity)

    @Query("DELETE FROM images_table")
    suspend fun deleteAll()

}
