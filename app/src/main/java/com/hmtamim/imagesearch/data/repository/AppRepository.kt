package com.hmtamim.imagesearch.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import com.hmtamim.imagesearch.data.room.entity.ImageEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val historyDao: HistoryDao) {

    /** repository for history database actions*/

    fun getHistories(search: String?): LiveData<List<ImageEntity>> {
        return if (search.isNullOrEmpty())
            historyDao.getAll()
        else
            historyDao.getAll(search)
    }

    @WorkerThread
    suspend fun insertHistory(entity: ImageEntity) = withContext(Dispatchers.IO) {
        historyDao.insert(entity)
    }

    @WorkerThread
    suspend fun deleteHistory(entity: ImageEntity) = withContext(Dispatchers.IO) {
        historyDao.delete(entity)
    }

    @WorkerThread
    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        historyDao.deleteAll()
    }

}