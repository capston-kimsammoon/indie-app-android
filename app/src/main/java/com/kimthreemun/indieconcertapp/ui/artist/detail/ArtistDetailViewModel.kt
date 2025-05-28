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

    // 목록에서 전달받은 Artist를 세팅
    fun setArtist(artist: Artist) {
        _artist.value = artist
        _isLiked.value = artist.isLiked
        _isNotified.value = artist.isNotified

        // TODO: artist.id 기준으로 공연 데이터 불러오기
        scheduledPerformances.value = artist.scheduledPerformances
        pastPerformances.value = artist.pastPerformances
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
