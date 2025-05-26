package com.kimthreemun.indieconcertapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class FavoriteViewModel : ViewModel() {
    private val _favoritePerformances = MutableLiveData<List<Performance>>()
    val favoritePerformances: LiveData<List<Performance>> = _favoritePerformances

    private val _favoriteArtists = MutableLiveData<List<Artist>>()
    val favoriteArtists: LiveData<List<Artist>> = _favoriteArtists

    init {
        val artists = listOf(
            Artist(1, "하츄핑", "https://example.com/image.png", R.drawable.sample_profile),
            Artist(2, "하츄핑", "https://example.com/image.png", R.drawable.sample_profile),
        )
        _favoriteArtists.value = artists

        _favoritePerformances.value = listOf(
            Performance(
                id = 1,
                title = "가장 한국적인 것이 가장 세계적이다다다다다다다!",
                venue = "CLUB FF",
                date = "2025.05.24 토요일",
                time = "14:00",
                region = "서울",
                artists = artists,  // ← 여기 충돌 해결
                price = "10000",
                ticketOpen = "2025.04.01",
                detailLink = "https://example.com/detail",
                posterUrl = "https://example.com/poster.jpg",
                dday = 8
            ),
            Performance(
                id = 2,
                title = "가장 한국적인 것이 가장 세계적이다다다다다다다!",
                venue = "CLUB FF",
                date = "2025.05.24 토요일",
                time = "14:00",
                region = "서울",
                artists = artists,
                price = "10000",
                ticketOpen = "2025.04.01",
                detailLink = "https://example.com/detail",
                posterUrl = "https://example.com/poster.jpg",
                dday = 8
            )
        )
    }

    fun toggleLike(artistId: Int) {
        _favoriteArtists.value = _favoriteArtists.value?.map {
            if (it.id == artistId) it.copy(isLiked = !it.isLiked) else it
        }
    }

    fun toggleNotify(artistId: Int) {
        _favoriteArtists.value = _favoriteArtists.value?.map {
            if (it.id == artistId) it.copy(isNotified = !it.isNotified) else it
        }
    }
}
