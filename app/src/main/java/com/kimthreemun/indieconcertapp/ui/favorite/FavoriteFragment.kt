// /ui/favorite/list/FavoriteFragment.kt
// TabLayout ViewPager2 연결
// 탭 전환 (공연 / 아티스트)

package com.kimthreemun.indieconcertapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.kimthreemun.indieconcertapp.databinding.FragmentFavoriteListBinding

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteListBinding? = null
    private val binding get() = _binding!!

    private lateinit var pagerAdapter: FavoritePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagerAdapter = FavoritePagerAdapter(this)
        binding.viewPagerFavorites.adapter = pagerAdapter

        val tabTitles = listOf("공연", "아티스트")

        TabLayoutMediator(binding.tabFavorites, binding.viewPagerFavorites) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
