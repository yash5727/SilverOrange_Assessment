package com.example.silverorange_assessment.domain.usecase

import com.example.silverorange_assessment.data.model.VideoPlayerItem
import com.example.silverorange_assessment.domain.repository.VideoPlayerRepository
import kotlinx.coroutines.flow.Flow

class GetVideosUseCase(
    private val videoPlayerRepository: VideoPlayerRepository
) {
    suspend fun execute(): Flow<List<VideoPlayerItem>> {
        return videoPlayerRepository.getVideosList()
    }
}