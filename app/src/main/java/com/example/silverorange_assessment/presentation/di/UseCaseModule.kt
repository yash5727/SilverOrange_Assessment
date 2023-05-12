package com.example.silverorange_assessment.presentation.di

import com.example.silverorange_assessment.domain.repository.VideoPlayerRepository
import com.example.silverorange_assessment.domain.usecase.GetVideosUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideVideoPlayerListUseCase(videoPlayerRepository: VideoPlayerRepository): GetVideosUseCase {
        return GetVideosUseCase(videoPlayerRepository)
    }
}