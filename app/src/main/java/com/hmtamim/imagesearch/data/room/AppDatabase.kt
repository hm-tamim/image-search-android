package com.hmtamim.imagesearch.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import com.hmtamim.imagesearch.data.room.entity.HistoryEntity

@Database(
    entities = [HistoryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun historyDao(): HistoryDao

    companion object {
        const val DATABASE_NAME: String = "app_database"
    }

}
