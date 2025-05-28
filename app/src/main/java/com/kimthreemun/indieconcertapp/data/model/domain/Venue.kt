package com.kimthreemun.indieconcertapp.data.model.domain


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Venue(
    val id: Int,
    val name: String,
    val profileImageUrl: String="",
    val profileImageResId: Int? = null,
    val region: String = "",
    var scheduledPerformances: List<Performance> = emptyList(),
    val instagramHandle: String="",
) : Parcelable
