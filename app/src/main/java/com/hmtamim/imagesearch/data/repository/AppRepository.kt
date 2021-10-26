package com.hmtamim.imagesearch.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import com.hmtamim.imagesearch.data.room.entity.HistoryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val historyDao: HistoryDao) {

    /** repository for history database actions*/

    fun getHistories(search: String?): LiveData<List<HistoryEntity>> {
        return if (search.isNullOrEmpty())
            historyDao.getAll()
        else
            historyDao.getAll(search)
    }

    @WorkerThread
    suspend fun insertHistory(entity: HistoryEntity) = withContext(Dispatchers.IO) {
        historyDao.insert(entity)
    }

    @WorkerThread
    suspend fun deleteHistory(entity: HistoryEntity) = withContext(Dispatchers.IO) {
        historyDao.delete(entity)
    }

    @WorkerThread
    suspend fun deleteAll() = withContext(Dispatchers.IO) {
        historyDao.deleteAll()
    }

}