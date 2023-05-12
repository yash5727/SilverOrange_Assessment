package com.example.silverorange_assessment.presentation.di

import com.example.silverorange_assessment.data.api.VideoPlayerService
import com.example.silverorange_assessment.data.repository.datasource.RemoteDatasource
import com.example.silverorange_assessment.data.repository.datasourceImpl.RemoteDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideNewsRemoteDatasource(videoPlayerService: VideoPlayerService): RemoteDatasource {
        return RemoteDatasourceImpl(videoPlayerService)
    }
}