package com.kimthreemun.indieconcertapp.data.model.domain

data class Comment(
    val id: Int, // 댓글 id
    val postId: Int, // 게시물 id
    val nickname: String, // 닉네임
    val profileUrl: String, // 프로필 이미지 url
    val content: String, // 내용
    val createdAt: String, // 작성 시간
    val parentCommentId: Int? = null // null이면 원댓글, 값 있으면 대댓글
)