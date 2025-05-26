package com.kimthreemun.indieconcertapp.ui.artist.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class ArtistDetailViewModel : ViewModel() {

    private val _artist = MutableLiveData<Artist>()
    val artist: LiveData<Artist> = _artist

    val scheduledPerformances = MutableLiveData<List<Performance>>()
    val pastPerformances = MutableLiveData<List<Performance>>()

    private val _isLiked = MutableLiveData(false)
    val isLiked: LiveData<Boolean> = _isLiked

    private val _likeCount = MutableLiveData(0)
    val likeCount: LiveData<Int> = _likeCount

    private val _isNotified = MutableLiveData(false)
    val isNotified: LiveData<Boolean> = _isNotified

    fun loadArtistDetail(artistId: Int) {
        _artist.value = Artist(
            id = artistId,
            name = "리락쿠마",
            profileImageUrl = "",
            spotifyUrl = "https://open.spotify.com",
            instagramHandle = "@rilakkuma",
            isLiked = false,
            isNotified = false
        )

        scheduledPerformances.value = listOf(
            Performance(id = 1, title = "예정 공연 A", date = "2025.06.01", posterImageResId = R.drawable.sample_poster),
            Performance(id = 2, title = "예정 공연 B", date = "2025.06.10", posterImageResId = R.drawable.sample_poster),
        )

        pastPerformances.value = listOf(
            Performance(id = 3, title = "지난 공연 A", date = "2025.05.10", posterImageResId = R.drawable.sample_poster),
            Performance(id = 4, title = "지난 공연 B", date = "2025.04.20", posterImageResId = R.drawable.sample_poster),
        )

        _isLiked.value = false
        _likeCount.value = 9
        _isNotified.value = false
    }

    fun toggleLike() {
        val liked = _isLiked.value ?: false
        _isLiked.value = !liked
        _likeCount.value = (_likeCount.value ?: 0) + if (!liked) 1 else -1
    }

    fun toggleNotify() {
        _isNotified.value = !(_isNotified.value ?: false)
    }
}
