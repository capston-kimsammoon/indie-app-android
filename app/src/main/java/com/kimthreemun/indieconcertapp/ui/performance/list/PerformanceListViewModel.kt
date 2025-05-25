package com.kimthreemun.indieconcertapp.ui.performance.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class PerformanceListViewModel : ViewModel() {

    private val _performances = MutableLiveData<List<Performance>>()
    val performances: LiveData<List<Performance>> get() = _performances

    private var allPerformances: List<Performance> = listOf() // 전체 공연 데이터

    // 날짜 파서
    private val dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd E", Locale.KOREAN)

    fun updatePerformances(newList: List<Performance>) {
        allPerformances = newList
        _performances.value = newList
    }

    fun sortBy(option: String) {
        val sortedList = when (option) {
            "공연일순" -> {
                allPerformances.sortedBy {
                    try {
                        LocalDate.parse(it.date, dateFormatter)
                    } catch (e: Exception) {
                        LocalDate.MIN
                    }
                }
            }
            "최근등록순" -> allPerformances // 등록일 정렬은 미구현 상태
            else -> allPerformances
        }
        _performances.value = sortedList
    }

    fun filterByRegions(regions: List<String>) {
        if (regions.isEmpty() || regions.contains("전체")) {
            _performances.value = allPerformances
        } else {
            val filtered = allPerformances.filter { performance ->
                regions.any { region ->
                    performance.region.contains(region, ignoreCase = true)
                }
            }
            _performances.value = filtered
        }
    }

    init {
        loadPerformances()
    }

    private fun loadPerformances() {
        val dummyArtist = Artist(
            id = 1,
            name = "아티스트 이름",
            profileImageUrl = "https://example.com/artist.jpg",
            profileImageResId = R.drawable.sample_profile
        )


        _performances.value = listOf(
            Performance(
                id = 1,
                title = "운해몽1",
                venue = "인터플레이",
                date = "2025.05.10 토요일",
                time = "14:00",
                region = "서울",
                artists = listOf(dummyArtist),
                price = "10000",
                ticketOpen = "2025.04.01",
                detailLink = "https://example.com/detail",
                posterUrl = "https://example.com/poster.jpg",
                dday = 8
            ),
            Performance(
                id = 2,
                title = "운해몽2",
                venue = "인터플레이",
                date = "2025.05.11 일요일",
                time = "14:00",
                region = "부산",
                artists = listOf(dummyArtist),
                price = "10000",
                ticketOpen = "2025.04.01",
                detailLink = "https://example.com/detail",
                posterUrl = "https://example.com/poster.jpg",
                dday = 8
            ),
            Performance(
                id = 3,
                title = "운해몽3",
                venue = "인터플레이",
                date = "2025.05.12 화요일",
                time = "14:00",
                region = "인천",
                artists = listOf(dummyArtist),
                price = "10000",
                ticketOpen = "2025.04.01",
                detailLink = "https://example.com/detail",
                posterUrl = "https://example.com/poster.jpg",
                dday = 8
            )

        ).also {
            allPerformances = it
        }
    }
}
