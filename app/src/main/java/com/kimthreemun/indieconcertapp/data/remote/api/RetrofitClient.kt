package com.kimthreemun.indieconcertapp.data.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // 👇 개발 중이면 이걸로 사용
    private const val BASE_URL = "http://10.0.2.2:8080"

    // 👇 배포용 서버를 테스트할 경우
    // private const val BASE_URL = "https://api.indieconcert.com"

    val authService: AuthService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthService::class.java)
    }
}
