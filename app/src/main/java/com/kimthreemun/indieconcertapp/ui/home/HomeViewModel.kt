package com.kimthreemun.indieconcertapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcert.data.model.domain.Concert

class HomeViewModel : ViewModel() {

    private val _concerts = MutableLiveData<List<Concert>>()
    val concerts: LiveData<List<Concert>> get() = _concerts

    private val _ticketOpenConcerts = MutableLiveData<List<Concert>>()
    val ticketOpenConcerts: LiveData<List<Concert>> get() = _ticketOpenConcerts

    private val allConcerts = getDummyConcerts() // 모든 공연 데이터
    private var selectedRegion: String = "전체"    // 기본값은 전체

    init {
        updateConcertsForRegion(selectedRegion)
    }

    // ✅ 지역별 필터링 기능
    fun updateConcertsForRegion(region: String) {
        selectedRegion = region
        val filtered = if (region == "전체") {
            allConcerts
        } else {
            allConcerts.filter { it.region == region }
        }
        _concerts.value = filtered
        _ticketOpenConcerts.value = filtered.filter { !it.ticketOpenDate.isNullOrBlank() }
    }

    private fun getDummyConcerts(): List<Concert> {
        return listOf(
            Concert("heaven is there", "https://i.imgur.com/ZLUspKR.jpg", "유기체", "2025-05-24", "2025-05-30", "강원"),
            Concert("Blue turtle land galaxy express", "https://i.imgur.com/mAGPZ1X.jpg", "생기스튜디오", "2025-05-24", "2025-05-30", "서울"),
            Concert("open mic", "https://i.imgur.com/kKxY0sN.jpg", "고요의방", "2025-05-24", null, "대구"),
            Concert("Bluegogidisco hwakin poster", "https://i.imgur.com/ATbFH0Z.jpg", "스트레인지프룻", "2025-05-24", "2025-05-30", "경기"),
            Concert("playlist#you are you", "https://i.imgur.com/kuDxcWv.jpg", "살롱문보우", "2025-05-24", null, "서울"),
            Concert("화노:Action 발매 기념 공연", "https://i.imgur.com/FeDsUqY.jpg", "유기체", "2025-05-24", null, "서울")
        )
    }
}
