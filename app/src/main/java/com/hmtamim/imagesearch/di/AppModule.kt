package com.hmtamim.imagesearch.di

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

}