package com.example.silverorange_assessment.presentation.di

import android.app.Application
import com.example.silverorange_assessment.domain.usecase.GetVideosUseCase
import com.example.silverorange_assessment.presentation.viewmodel.VideoPlayerViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideVideoPlayerViewModelFactory(
        application: Application,
        getVideosUseCase: GetVideosUseCase
    ): VideoPlayerViewModelFactory {
        return VideoPlayerViewModelFactory(
            application,
            getVideosUseCase)
    }
}