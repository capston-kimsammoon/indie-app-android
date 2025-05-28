package com.kimthreemun.indieconcertapp.data.model.domain

data class Venue(
    val id: Int,
    val name: String,
    val profileImageUrl: String="",
    val profileImageResId: Int? = null,
    val instagramHandle: String=""
)
