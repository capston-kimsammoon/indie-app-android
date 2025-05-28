// FavoritePagerAdapter.kt
package com.kimthreemun.indieconcertapp.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kimthreemun.indieconcertapp.ui.favorite.FavoritePerformanceFragment
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteArtistFragment

class FavoritePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoritePerformanceFragment()
            1 -> FavoriteArtistFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
}
