package com.kimthreemun.indieconcertapp.data.model.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Post(
    val id: Int,
    val nickname: String,
    val profileUrl: String,
    val createdAt: String,
    val title: String,
    val content: String,
    val commentCount: Int,
    val imageUrl: String? = null
) : Parcelable
