package com.hmtamim.imagesearch.di

import android.content.Context
import androidx.room.Room
import com.hmtamim.imagesearch.data.repository.AppRepository
import com.hmtamim.imagesearch.data.room.AppDatabase
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    /** dagger hilt dependency injection for MVVM architecture*/

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        @Qualifiers.DatabaseName databaseName: String
    ): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, databaseName)
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideHistoryDao(appDatabase: AppDatabase): HistoryDao {
        return appDatabase.historyDao();
    }

    @Provides
    @Singleton
    fun provideAppRepository(historyDao: HistoryDao): AppRepository {
        return AppRepository(historyDao);
    }

}