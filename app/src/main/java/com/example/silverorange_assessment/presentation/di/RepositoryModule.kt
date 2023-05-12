package com.example.silverorange_assessment.presentation.di

import com.example.silverorange_assessment.data.repository.VideoPlayerRepositoryImpl
import com.example.silverorange_assessment.data.repository.datasource.RemoteDatasource
import com.example.silverorange_assessment.domain.repository.VideoPlayerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideNewsRepository(
        remoteDatasource: RemoteDatasource
    ): VideoPlayerRepository {
        return VideoPlayerRepositoryImpl(remoteDatasource)
    }
}