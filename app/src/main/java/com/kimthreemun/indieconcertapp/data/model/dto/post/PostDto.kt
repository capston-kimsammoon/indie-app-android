package com.kimthreemun.indieconcertapp.data.model.dto.post

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PostDto(
    val id: String,
    val title: String,
    val content: String,
    val author: String,
    val date: String,
    val comments: List<String> = listOf() // 간단히 문자열 댓글로 예시
) : Parcelable