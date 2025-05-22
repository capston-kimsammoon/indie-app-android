// /ui/performance/list/ArtistDetailViewModel.kt
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

    fun loadArtistDetail(artistId: Int) {
        _artist.value = Artist(
            id = artistId,
            name = "리락쿠마",
            profileImageUrl = "",  // 테스트용 URL
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
    }


    fun toggleLike() {
        _artist.value = _artist.value?.copy(isLiked = !_artist.value!!.isLiked)
        // repository에 좋아요 상태 저장 호출
    }

    fun toggleNotify() {
//        _artist.value = _artist.value?.copy(isNotified = !_artist.value!!.isNotified)
        // repository에 알림 설정 저장 호출
    }
}
