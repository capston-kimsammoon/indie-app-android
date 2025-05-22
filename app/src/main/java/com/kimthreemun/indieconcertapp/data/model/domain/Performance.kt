// data/model/domain/Performance.kt
package com.kimthreemun.indieconcertapp.data.model.domain

data class Performance(
    val id: Int,
    val title: String,
    val date: String,
    val venue: String = "",
    val time: String = "",
    val region: String = "",
    val artists: List<String> = emptyList(),
    val price: String = "",
    val ticketOpen: String = "",
    val detailLink: String = "",
    val posterUrl: String = "",
    val dday: Int = 0,
    val posterImageResId: Int? = null
)
