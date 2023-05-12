package com.example.silverorange_assessment.data.repository.datasource

import com.example.silverorange_assessment.data.model.VideoPlayerItem
import kotlinx.coroutines.flow.Flow

interface RemoteDatasource {
    suspend fun getVideosList(): Flow<List<VideoPlayerItem>>
}