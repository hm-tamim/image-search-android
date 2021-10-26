package com.hmtamim.imagesearch.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.hmtamim.imagesearch.data.room.entity.HistoryEntity

@Dao
interface HistoryDao {

    @Query("SELECT * FROM histories ORDER BY date DESC")
    fun getAll(): LiveData<List<HistoryEntity>>

    @Query("SELECT * FROM histories WHERE url LIKE '%' || :search || '%' ORDER BY date DESC")
    fun getAll(search: String): LiveData<List<HistoryEntity>>

    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)

    @Query("DELETE FROM histories")
    suspend fun deleteAll()
}
