// FavoriteArtistFragment.kt
package com.kimthreemun.indieconcertapp.ui.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentFavoriteArtistBinding
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteViewModel
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteArtistAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteArtistFragment : Fragment() {

    private var _binding: FragmentFavoriteArtistBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoriteArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteArtistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FavoriteArtistAdapter(
            emptyList(),
            onHeartClick = { artist ->
                viewModel.toggleLike(artist.id)
            },
            onNotifyClick = { artist ->
                viewModel.toggleNotify(artist.id)
            }
        )
        binding.rvFavoriteArtists.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoriteArtists.adapter = adapter

        // 구분선 추가
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.rvFavoriteArtists.addItemDecoration(divider)

        viewModel.favoriteArtists.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

