// data/model/domain/Performance.kt
package com.kimthreemun.indieconcertapp.data.model.domain

data class Performance(
    val id: Int,
    val title: String = "",
    val date: String = "",
    val venue: String = "",
    val time: String = "",
    val region: String = "",
<<<<<<< HEAD
    val address: String = "",
    val artists: List<String> = emptyList(),
=======
    val artists: List<Artist> = emptyList(),
>>>>>>> cc933e52565fb768e843568f9a724c6c3aecf129
    val price: String = "",
    val ticketOpen: String = "",
    val detailLink: String = "",
    val posterUrl: String = "",
    val dday: Int = 0,
    val posterImageResId: Int? = null,
<<<<<<< HEAD
    var isSelected: Boolean = false
=======
    var isLiked: Boolean = false,
    var isNotified: Boolean = false
>>>>>>> cc933e52565fb768e843568f9a724c6c3aecf129
)
