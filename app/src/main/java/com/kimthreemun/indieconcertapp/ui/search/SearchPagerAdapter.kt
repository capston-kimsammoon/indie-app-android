package com.kimthreemun.indieconcertapp.ui.search

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SearchPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment = when (position) {
      //  0 -> PerformanceVenueFragment()
        1 -> ArtistFragment()
        //2 -> CommunityFragment()
        //3 -> RecentSearchFragment()
        else -> throw IllegalStateException("Invalid tab position")
    }
}
