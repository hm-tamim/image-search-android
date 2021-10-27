package com.hmtamim.imagesearch.di

import android.content.Context
import com.hmtamim.imagesearch.BuildConfig
import com.hmtamim.imagesearch.data.remote.ApiClient
import com.hmtamim.imagesearch.data.repository.ApiRepository
import com.hmtamim.imagesearch.utils.NetworkConnectionObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    /** dagger hilt dependency injection for MVVM architecture*/

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(120, TimeUnit.SECONDS)
        okHttpClient.readTimeout(120, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(120, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiRepository(apiClient: ApiClient): ApiRepository {
        return ApiRepository(apiClient)
    }

    @Provides
    @Singleton
    @Named("network_connection_livedata")
    fun provideNetworkConnectionObserver(@ApplicationContext context: Context): NetworkConnectionObserver {
        return NetworkConnectionObserver(context)
    }

}