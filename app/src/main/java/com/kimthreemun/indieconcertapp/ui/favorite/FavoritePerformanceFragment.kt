package com.kimthreemun.indieconcertapp.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.R
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
        adapter = FavoritePerformanceAdapter(
            performances = emptyList(),
            onItemClick = { performance ->
                 val action = FavoriteFragmentDirections
                     .actionFavoriteFragmentToPerformanceDetailFragment(performance)
                 findNavController().navigate(action)
            },
            onHeartClick = { performance ->
                viewModel.toggleLike(performance.id)
            }
        )

        binding.rvFavoritePerformances.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavoritePerformances.adapter = adapter

        // 구분선 추가
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.rvFavoritePerformances.addItemDecoration(divider)

        viewModel.favoritePerformances.observe(viewLifecycleOwner) {
            adapter.updateData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
