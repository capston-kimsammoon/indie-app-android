package com.kimthreemun.indieconcertapp.ui.venue.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class VenueDetailViewModel : ViewModel() {

    private val _venue = MutableLiveData<Venue>()
    val artist: LiveData<Venue> = _venue

    val scheduledPerformances = MutableLiveData<List<Performance>>()

    fun loadVenueDetail(venueId: Int) {
        _venue.value = Venue(
            id = venueId,
            name = "언플러그드 홍대점",
            profileImageUrl = "",  // 테스트용 URL
            instagramHandle = "@unplugged_stage",
        )

        scheduledPerformances.value = listOf(
            Performance(id = 1, title = "SWELL", date = "2025.06.10", posterImageResId = R.drawable.venue_sample2),
            Performance(id = 2, title = "CLUB WED'S DAY", date = "2025.06.11", posterImageResId = R.drawable.venue_sample3),
            Performance(id = 3, title = "시선이닫힌세계", date = "2025.06.14", posterImageResId = R.drawable.venue_sample4),
        )
    }
}
