package com.example.silverorange_assessment.domain.repository

import com.example.silverorange_assessment.data.model.VideoPlayerItem
import kotlinx.coroutines.flow.Flow

interface VideoPlayerRepository {
    suspend fun getVideosList(): Flow<List<VideoPlayerItem>>
}