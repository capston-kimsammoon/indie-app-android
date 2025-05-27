package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kimthreemun.indieconcertapp.R

class SearchActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)
        searchEditText = findViewById(R.id.etSearch)

        val adapter = SearchPagerAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "공연/공연장"
                1 -> "아티스트"
                2 -> "자유게시판"
                3 -> "최근검색어"
                else -> null
            }
        }.attach()

        searchEditText.setOnEditorActionListener { v, actionId, event ->
            val keyword = v.text.toString()
            // ViewModel or Fragment에 전달 로직 필요
            true
        }
    }
}
