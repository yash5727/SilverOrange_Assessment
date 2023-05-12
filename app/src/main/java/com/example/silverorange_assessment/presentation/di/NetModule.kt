package com.example.silverorange_assessment.presentation.di

import com.example.silverorange_assessment.data.api.VideoPlayerService
import com.example.silverorange_assessment.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideVideoPlayerApiService(retrofit: Retrofit): VideoPlayerService {
        return retrofit.create(VideoPlayerService::class.java)
    }
}