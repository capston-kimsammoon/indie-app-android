package com.kimthreemun.indieconcertapp.data.model.dto.user

data class LoginResponse(
    val userId: Long,
    val jwtToken: String
)
