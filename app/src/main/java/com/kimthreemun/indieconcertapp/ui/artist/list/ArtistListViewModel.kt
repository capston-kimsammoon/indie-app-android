package com.kimthreemun.indieconcertapp.ui.artist.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R

import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class ArtistListViewModel : ViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> get() = _artists

    init {
        _artists.value = listOf(
            Artist(1, "하츄핑", "https://example.com/image.png", R.drawable.sample_profile),
            Artist(2, "하츄핑", "https://example.com/image.png", R.drawable.sample_profile),

            )
    }

    fun toggleLike(artistId: Int) {
        _artists.value = _artists.value?.map {
            if (it.id == artistId) it.copy(isLiked = !it.isLiked) else it
        }
    }
}
