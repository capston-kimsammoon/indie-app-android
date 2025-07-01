package com.kimthreemun.indieconcertapp.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// 카카오 리다이렉트 처리용 (실제 UI 없음)
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 아무 UI 필요 없음 — 로그인 Fragment에서 처리됨
        finish() // 이 액티비티는 리다이렉트 받고 바로 닫힘
    }
}
