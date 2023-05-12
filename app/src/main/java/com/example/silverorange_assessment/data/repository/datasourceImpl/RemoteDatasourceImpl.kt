package com.example.silverorange_assessment.data.repository.datasourceImpl

import com.example.silverorange_assessment.data.api.VideoPlayerService
import com.example.silverorange_assessment.data.repository.datasource.RemoteDatasource
import kotlinx.coroutines.flow.flow

class RemoteDatasourceImpl(
    private val videoPlayerService: VideoPlayerService
): RemoteDatasource {
    override suspend fun getVideosList() = flow  {
        val response = videoPlayerService.getVideosList()
        emit(response)
    }
}