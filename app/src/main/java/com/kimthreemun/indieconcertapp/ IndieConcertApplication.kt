// com.kimthreemun.indieconcertapp/IndieConcertApplication.kt
package com.kimthreemun.indieconcertapp

import android.app.Application
import com.kimthreemun.indieconcertapp.data.sample.AssignSampleArtistPerformances
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IndieConcertApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "9af1bfd63591abaa1bb829d8d7b56032")

        AssignSampleArtistPerformances()
    }
}
