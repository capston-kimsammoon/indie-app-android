// /ui/performance/list/PerformanceDetailViewModel.kt
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

    private val _isLiked = MutableLiveData(false)
    val isLiked: LiveData<Boolean> = _isLiked

    private val _likeCount = MutableLiveData(0)
    val likeCount: LiveData<Int> = _likeCount

    fun loadPerformanceDetail(performanceId: Int) {
        _performanceDetail.value = Performance(
            id = performanceId,
            title = "Moskva Surfing Club",
            date = "2025.05.09 토요일 오후 8시",
            time = "오후 8시",
            region = "서울",
            venue = "cafe PPnF",
            artists = listOf(
                Artist(1, "하츄핑", "https://example.com/image.png", R.drawable.sample_profile)
            ).map { it.name },
            price = "1,000원",
            ticketOpen = "2025.05.01 목요일 오후 9시",
            detailLink = "https://www.instagram.com/kimthreemun",
            posterUrl = "https://example.com/poster.png",
            dday = 10
        )

        _isLiked.value = false
        _likeCount.value = 12
    }

    fun toggleLike() {
        val liked = _isLiked.value ?: false
        _isLiked.value = !liked
        _likeCount.value = (_likeCount.value ?: 0) + if (!liked) 1 else -1
    }
}

