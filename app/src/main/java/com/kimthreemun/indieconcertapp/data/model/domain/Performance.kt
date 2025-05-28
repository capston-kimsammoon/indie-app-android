// data/model/domain/Performance.kt
package com.kimthreemun.indieconcertapp.data.model.domain
import java.io.Serializable
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Performance(
    val id: Int,
    val title: String = "",
    val date: String = "",
    val venue: String = "",
    val time: String = "",
    val region: String = "",
    val address: String = "",
    val artists: List<String> = emptyList(),
    val price: String = "",
    val ticketOpenDate: String = "",
    val ticketOpenTime: String = "",
    val detailLink: String = "",
    val posterUrl: String = "",
    val dday: Int = 0,
    val posterImageResId: Int? = null,
    var isSelected: Boolean = false,
    var isLiked: Boolean = false,
    var likeCount: Int = 0,
    var isNotified: Boolean = false
) : Parcelable {

    fun copy(
        id: Int = this.id,
        title: String = this.title,
        date: String = this.date,
        venue: String = this.venue,
        time: String = this.time,
        region: String = this.region,
        artists: List<Artist> = this.artists,
        price: String = this.price,
        ticketOpenDate: String = this.ticketOpenDate,
        ticketOpenTime: String = this.ticketOpenTime,
        detailLink: String = this.detailLink,
        posterUrl: String = this.posterUrl,
        dday: Int = this.dday,
        posterImageResId: Int? = this.posterImageResId,
        isLiked: Boolean = this.isLiked,
        likeCount: Int = this.likeCount,
        isNotified: Boolean = this.isNotified
    ): Performance {
        return Performance(
            id,
            title,
            date,
            venue,
            time,
            region,
            artists,
            price,
            ticketOpenDate,
            ticketOpenTime,
            detailLink,
            posterUrl,
            dday,
            posterImageResId,
            isLiked,
            likeCount,
            isNotified
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Performance) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}