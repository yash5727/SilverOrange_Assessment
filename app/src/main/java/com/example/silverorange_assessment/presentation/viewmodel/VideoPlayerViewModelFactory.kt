package com.example.silverorange_assessment.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.silverorange_assessment.domain.usecase.GetVideosUseCase

class VideoPlayerViewModelFactory(
    private val app: Application,
    private val getVideosUseCase: GetVideosUseCase
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoPlayerViewModel(
            app,
            getVideosUseCase
        ) as T
    }
}