package com.kimthreemun.indieconcertapp.ui.mypage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MypageViewModel : ViewModel() {

    private val _isNotificationOn = MutableLiveData(false)
    val isNotificationOn: LiveData<Boolean> = _isNotificationOn

    private val _isLocationOn = MutableLiveData(false)
    val isLocationOn: LiveData<Boolean> = _isLocationOn

    fun toggleNotification() {
        _isNotificationOn.value = !_isNotificationOn.value!!
    }

    fun toggleLocation() {
        _isLocationOn.value = !_isLocationOn.value!!
    }
}