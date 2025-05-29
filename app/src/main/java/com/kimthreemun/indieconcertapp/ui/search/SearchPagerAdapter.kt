package com.kimthreemun.indieconcertapp.ui.search

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.fragment.app.FragmentActivity
import com.kimthreemun.indieconcertapp.ui.search.ArtistSearchFragment
import com.kimthreemun.indieconcertapp.ui.search.BoardSearchFragment
import com.kimthreemun.indieconcertapp.ui.search.ShowSearchFragment
//import com.kimthreemun.indieconcertapp.ui.search.VenueSearchFragment

class SearchPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ShowSearchFragment()
            1 -> VenueSearchFragment()
            2 -> ArtistSearchFragment()
            3 -> BoardSearchFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}