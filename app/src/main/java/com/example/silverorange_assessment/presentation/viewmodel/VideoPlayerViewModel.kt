package com.example.silverorange_assessment.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.silverorange_assessment.data.model.VideoPlayerItem
import com.example.silverorange_assessment.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoPlayerViewModel(
    app: Application,
    private val getVideosUseCase: GetVideosUseCase
) : AndroidViewModel(app) {
    var videos: MutableLiveData<List<VideoPlayerItem>> = MutableLiveData()

    var isVideoEnded = false

    var currentItem = 0
    var totalItems = 0

    var playbackPosition = 0L

    fun getVideosList() = viewModelScope.launch {
        getVideosUseCase.execute().catch {

        }.collect{
            withContext(Main){
                videos.value = it
            }
        }
    }
}