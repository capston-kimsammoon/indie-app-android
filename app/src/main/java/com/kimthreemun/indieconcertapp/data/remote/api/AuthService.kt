package com.kimthreemun.indieconcertapp.data.remote.api

import com.kimthreemun.indieconcertapp.data.model.dto.user.KakaoLoginRequest
import com.kimthreemun.indieconcertapp.data.model.dto.user.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("/auth/kakao") // 백엔드 API 경로
    suspend fun loginWithKakao(
        @Body request: KakaoLoginRequest
    ): Response<LoginResponse>
}
