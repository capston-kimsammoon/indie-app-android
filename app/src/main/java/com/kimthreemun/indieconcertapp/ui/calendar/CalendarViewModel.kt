package com.kimthreemun.indieconcertapp.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcert.data.model.domain.Concert
import java.time.LocalDate

class CalendarViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData<LocalDate?>()
    val selectedDate: LiveData<LocalDate?> = _selectedDate

    private val _selectedRegions = MutableLiveData<Set<String>>(emptySet())
    val selectedRegions: LiveData<Set<String>> = _selectedRegions

    private val _filteredConcerts = MutableLiveData<List<Concert>>()
    val filteredConcerts: LiveData<List<Concert>> = _filteredConcerts

    val allConcerts = listOf(
        Concert("heaven is there", "https://i.imgur.com/ZLUspKR.jpg", "유기체", "2025-05-03", region = "강원"),
        Concert("Blue turtle land galaxy express", "https://i.imgur.com/mAGPZ1X.jpg", "생기스튜디오", "2025-05-14", region = "서울"),
        Concert("open mic", "https://i.imgur.com/kKxY0sN.jpg", "고요의방", "2025-05-14", region = "강원"),
        Concert("open mic", "https://i.imgur.com/kKxY0sN.jpg", "고요의방", "2025-05-28", region = "강원"),
        Concert("Bluegogidisco hwakin poster", "https://i.imgur.com/ATbFH0Z.jpg", "스트레인지프룻", "2025-05-13", region = "인천"),
        Concert("playlist#you are you", "https://i.imgur.com/kuDxcWv.jpg", "살롱문보우", "2025-05-28", region = "서울"),
        Concert("화노:Action 발매 기념 공연", "https://i.imgur.com/FeDsUqY.jpg", "유기체", "2025-05-28", region = "경기"),
        Concert("원호와 타임머신", "https://i.imgur.com/rSejFp9.jpg", "유기체", "2025-05-28", region = "서울"),
        Concert("우리는 초록 속에서", "https://i.imgur.com/XLGuTYe.jpg", "언플러그드", "2025-05-30", region = "서울")
    )

    init {
        val today = LocalDate.now()
        selectDate(today)
    }

    fun selectDate(date: LocalDate) {
        _selectedDate.value = date
        val regions = _selectedRegions.value.orEmpty()

        _filteredConcerts.value = allConcerts.filter {
            it.date == date.toString() &&
                    (regions.isEmpty() || regions.contains(it.region))
        }
    }

    fun updateRegionFilter(newRegions: Set<String>) {
        _selectedRegions.value = newRegions
        _selectedDate.value?.let { selectDate(it) }
    }

    fun getConcertDates(): Set<LocalDate> {
        // ✅ 여기서도 지역 필터 고려
        val regions = _selectedRegions.value.orEmpty()
        return allConcerts
            .filter { regions.isEmpty() || regions.contains(it.region) }
            .map { LocalDate.parse(it.date) }
            .toSet()
    }
}
