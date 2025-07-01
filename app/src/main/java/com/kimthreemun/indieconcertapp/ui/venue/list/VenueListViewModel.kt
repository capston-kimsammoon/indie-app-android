package com.kimthreemun.indieconcertapp.ui.venue.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.data.model.domain.Venue

class VenueListViewModel : ViewModel() {
    private var originalList: List<Venue> = emptyList()

    private val _filteredVenues = MutableLiveData<List<Venue>>()
    val filteredVenues: LiveData<List<Venue>> get() = _filteredVenues

    fun setOriginalVenues(venues: List<Venue>) {
        originalList = venues
        _filteredVenues.value = venues
    }

    fun filterByRegions(selected: List<String>) {
        if (selected.isEmpty() || selected.contains("전체")) {
            _filteredVenues.value = originalList
        } else {
            _filteredVenues.value = originalList.filter { selected.contains(it.region) }
        }
    }
}
