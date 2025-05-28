package com.kimthreemun.indieconcertapp.ui.performance.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class PerformanceDetailViewModel : ViewModel() {

    private val _performanceDetail = MutableLiveData<Performance>()
    val performanceDetail: LiveData<Performance> = _performanceDetail

    fun setPerformance(performance: Performance) {
        _performanceDetail.value = performance
    }

    fun toggleLike() {
        val current = _performanceDetail.value ?: return
        val newLiked = !current.isLiked
        val newLikeCount = current.likeCount + if (newLiked) 1 else -1

        _performanceDetail.value = current.copy(
            isLiked = newLiked,
            likeCount = newLikeCount
        )
    }

    fun toggleNotify() {
        val current = _performanceDetail.value ?: return
        _performanceDetail.value = current.copy(
            isNotified = !current.isNotified
        )
    }
}
