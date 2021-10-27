package com.hmtamim.imagesearch.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val historyDao: HistoryDao) {

    /** repository for history database actions*/

    fun getAllPhotosLive(query: String): LiveData<List<ImageEntity>> {
        return historyDao.getAll(query)
    }

    @WorkerThread
    suspend fun getAllPhotos(query: String): List<ImageEntity> {
        return historyDao.getAllList(query)
    }

    @WorkerThread
    suspend fun insertPhoto(entity: ImageEntity) = withContext(Dispatchers.IO) {
        historyDao.insert(entity)
    }

    @WorkerThread
    suspend fun insertAllPhotos(entities: List<ImageEntity>) = withContext(Dispatchers.IO) {
        historyDao.insertAll(entities)
    }

    @WorkerThread
    suspend fun deletePhoto(entity: ImageEntity) = withContext(Dispatchers.IO) {
        historyDao.delete(entity)
    }

    @WorkerThread
    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        historyDao.deleteAll()
    }

}