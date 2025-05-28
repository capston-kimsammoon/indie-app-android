// com.kimthreemun.indieconcertapp/IndieConcertApplication.kt
package com.kimthreemun.indieconcertapp

import android.app.Application
import com.kimthreemun.indieconcertapp.data.sample.AssignSampleArtistPerformances
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IndieConcertApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AssignSampleArtistPerformances()
    }
}
