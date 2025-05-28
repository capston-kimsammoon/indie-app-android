package com.kimthreemun.indieconcertapp.data.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Artist(
    val id: Int,
    val name: String,
    val profileImageUrl: String = "",
    val profileImageResId: Int? = null,
    val spotifyUrl: String = "",
    val instagramHandle: String = "",
    var isLiked: Boolean = false,
    var isNotified: Boolean = false,
    var scheduledPerformances: List<Performance> = emptyList(),
    var pastPerformances: List<Performance> = emptyList()
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Artist) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    fun copy(
        id: Int = this.id,
        name: String = this.name,
        profileImageUrl: String = this.profileImageUrl,
        profileImageResId: Int? = this.profileImageResId,
        spotifyUrl: String = this.spotifyUrl,
        instagramHandle: String = this.instagramHandle,
        isLiked: Boolean = this.isLiked,
        isNotified: Boolean = this.isNotified,
        scheduledPerformances: List<Performance> = this.scheduledPerformances,
        pastPerformances: List<Performance> = this.pastPerformances
    ): Artist {
        return Artist(
            id,
            name,
            profileImageUrl,
            profileImageResId,
            spotifyUrl,
            instagramHandle,
            isLiked,
            isNotified,
            scheduledPerformances,
            pastPerformances
        )
    }
}
