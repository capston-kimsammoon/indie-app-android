// /ui/MainActivity.kt
package com.kimthreemun.indieconcertapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListFragment
import com.kimthreemun.indieconcertapp.ui.performance.detail.PerformanceDetailFragment
import com.kimthreemun.indieconcertapp.ui.artist.list.ArtistListFragment
import com.kimthreemun.indieconcertapp.ui.artist.detail.ArtistDetailFragment
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 첫 진입 시 fragment_performance_list.xml 붙이기
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, ArtistDetailFragment())
                .commit()
        }

    }
}
