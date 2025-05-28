package com.kimthreemun.indieconcertapp.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

import com.kimthreemun.indieconcertapp.data.sample.SamplePerformances
import com.kimthreemun.indieconcertapp.data.sample.SampleArtists

class FavoriteViewModel : ViewModel() {
    private val _favoritePerformances = MutableLiveData<List<Performance>>()
    val favoritePerformances: LiveData<List<Performance>> = _favoritePerformances

    private val _favoriteArtists = MutableLiveData<List<Artist>>()
    val favoriteArtists: LiveData<List<Artist>> = _favoriteArtists


    fun toggleLike(artistId: Int) {
        SampleArtists.all.find { it.id == artistId }?.let { it.isLiked = !it.isLiked }
        loadArtists()
    }

    fun togglePerformanceLike(performanceId: Int) {
        SamplePerformances.all.find { it.id == performanceId }?.let { it.isLiked = !it.isLiked }
        loadPerformances()
    }


    init {
        loadArtists()
        loadPerformances()
    }

    private fun loadArtists() {
        _favoriteArtists.value = SampleArtists.all.filter { it.isLiked }
    }

    private fun loadPerformances() {
        _favoritePerformances.value = SamplePerformances.all.filter { it.isLiked }
    }


    fun toggleNotify(artistId: Int) {
        _favoriteArtists.value = _favoriteArtists.value?.map {
            if (it.id == artistId) it.copy(isNotified = !it.isNotified) else it
        }
    }
}
