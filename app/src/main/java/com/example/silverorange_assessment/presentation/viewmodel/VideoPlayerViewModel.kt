package com.example.silverorange_assessment.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.silverorange_assessment.data.model.VideoPlayerItem
import com.example.silverorange_assessment.domain.usecase.GetVideosUseCase
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VideoPlayerViewModel(
    private val getVideosUseCase: GetVideosUseCase
) : ViewModel() {
    var videos: MutableLiveData<List<VideoPlayerItem>> = MutableLiveData()

    fun getVideosList() = viewModelScope.launch {
        getVideosUseCase.execute().catch {

        }.collect{
            withContext(Main){
                videos.value = it
            }
        }
    }
}