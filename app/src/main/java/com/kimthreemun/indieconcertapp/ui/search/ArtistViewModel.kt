package com.kimthreemun.indieconcertapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ArtistViewModel : ViewModel() {
    private val _artistList = MutableLiveData<List<String>>()
    val artistList: LiveData<List<String>> get() = _artistList

    fun search(query: String) {
        // 검색어로 API 호출 또는 필터링
        _artistList.value = listOf("김삼문", "김밥삼문").filter { it.contains(query) }
    }
}
