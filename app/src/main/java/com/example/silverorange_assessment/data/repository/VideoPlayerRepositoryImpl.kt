package com.example.silverorange_assessment.data.repository

import com.example.silverorange_assessment.data.model.VideoPlayerItem
import com.example.silverorange_assessment.domain.repository.VideoPlayerRepository
import com.example.silverorange_assessment.data.repository.datasource.RemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

class VideoPlayerRepositoryImpl(
    private val remoteDatasource: RemoteDatasource
) : VideoPlayerRepository {
    override suspend fun getVideosList(): Flow<List<VideoPlayerItem>> {
        return remoteDatasource.getVideosList().transform {
            this.emit(it)
        }
    }
}