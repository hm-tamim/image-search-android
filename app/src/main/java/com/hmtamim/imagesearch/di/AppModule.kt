package com.hmtamim.imagesearch.di

import com.hmtamim.imagesearch.data.repository.AppRepository
import com.hmtamim.imagesearch.data.room.AppDatabase
import com.hmtamim.imagesearch.data.room.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /** dagger hilt dependency injection for MVVM architecture*/

    @Qualifiers.DatabaseName
    @Singleton
    @Provides
    fun provideDatabaseName(): String {
        return "app_database"
    }

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