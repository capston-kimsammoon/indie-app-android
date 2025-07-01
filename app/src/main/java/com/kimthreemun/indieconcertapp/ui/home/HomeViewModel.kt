package com.kimthreemun.indieconcertapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class HomeViewModel : ViewModel() {

    private val _performances = MutableLiveData<List<Performance>>()
    val performances: LiveData<List<Performance>> = _performances

    private val _ticketOpenPerformances = MutableLiveData<List<Performance>>()
    val ticketOpenPerformances: LiveData<List<Performance>> get() = _ticketOpenPerformances

    private val allPerformances = getDummyPerformances() // 모든 공연 데이터
    private var selectedRegion: String = "전체"    // 기본값은 전체

    init {
        updatePerformancesForRegion(selectedRegion)
    }

    // 지역별 필터링 기능
    fun updatePerformancesForRegion(region: String) {
        selectedRegion = region
        val filtered = if (region == "전체") {
            allPerformances
        } else {
            allPerformances.filter { it.region == region }
        }
        _performances.value = filtered
        _ticketOpenPerformances.value = filtered.filter { !it.ticketOpenDate.isNullOrBlank() }
    }

    private fun getDummyPerformances(): List<Performance> {
        return listOf(
            Performance(
                id=1,
                title = "heaven is there",
                posterUrl = "https://i.imgur.com/ZLUspKR.jpg",
                venue = "유기체",
                ticketOpenDate = "2025-06-30",
                time = "2025-05-30",
                region = "강원"
            ),
            Performance(
                id=2,
                title = "Blue turtle land galaxy express",
                posterUrl = "https://i.imgur.com/mAGPZ1X.jpg",
                venue = "생기스튜디오",
                ticketOpenDate = "2025-06-30",
                time = "2025-05-30",
                region = "서울"
            ),
            Performance(
                id=3,
                title = "open mic",
                posterUrl = "https://i.imgur.com/kKxY0sN.jpg",
                venue = "고요의방",
                ticketOpenDate = "2025-06-30",
                time = "2025-06-20",
                region = "대구"
            ),
            Performance(
                id=4,
                title = "Bluegogidisco hwakin poster",
                posterUrl = "https://i.imgur.com/ATbFH0Z.jpg",
                venue = "스트레인지프룻",
                ticketOpenDate = "2025-06-30",
                time = "2025-05-30",
                region = "경기"
            ),
            Performance(
                id=5,
                title = "playlist#you are you",
                posterUrl = "https://i.imgur.com/kuDxcWv.jpg",
                venue = "살롱문보우",
                ticketOpenDate = "2025-06-30",
                time = "2025-05-30",
                region = "서울"
            ),
            Performance(
                id=6,
                title = "화노:Action 발매 기념 공연",
                posterUrl = "https://i.imgur.com/FeDsUqY.jpg",
                venue = "유기체",
                ticketOpenDate = "2025-06-30",
                time = "2025-05-30",
                region = "서울"
            )
        )
    }
}
