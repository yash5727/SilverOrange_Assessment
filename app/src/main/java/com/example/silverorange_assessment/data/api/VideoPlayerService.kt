package com.example.silverorange_assessment.data.api

import com.example.silverorange_assessment.data.model.VideoPlayerItem
import retrofit2.http.GET

interface VideoPlayerService {
    @GET("/videos")
    suspend fun getVideosList(
    ): List<VideoPlayerItem>
}