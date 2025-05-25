// data/model/domain/Artist.kt
package com.kimthreemun.indieconcertapp.data.model.domain

data class Artist(
    val id: Int,
    val name: String,
    val profileImageUrl: String="",
    val profileImageResId: Int? = null,
    val spotifyUrl: String="",
    val instagramHandle: String="",
    var isLiked: Boolean = false,
    var isNotified: Boolean = false
)