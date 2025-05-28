package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kimthreemun.indieconcertapp.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SearchFragment())
                .commit()
        }
    }

}
