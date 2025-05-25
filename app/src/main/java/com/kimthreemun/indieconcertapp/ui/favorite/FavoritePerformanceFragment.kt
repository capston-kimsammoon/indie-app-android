// FavoritePerformanceFragment.kt
package com.kimthreemun.indieconcertapp.ui.favorite.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.FragmentFavoritePerformanceBinding
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteViewModel
import com.kimthreemun.indieconcertapp.ui.favorite.FavoritePerformanceAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritePerformanceFragment : Fragment() {

    private var _binding: FragmentFavoritePerformanceBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private lateinit var adapter: FavoritePerformanceAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritePerformanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = FavoritePerformanceAdapter(emptyList()) { performance ->

        }
        binding.rvFavoritePerformances.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritePerformances.adapter = adapter

        viewModel.favoritePerformances.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
