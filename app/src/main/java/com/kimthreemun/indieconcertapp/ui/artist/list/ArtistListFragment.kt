package com.kimthreemun.indieconcertapp.ui.artist.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.databinding.FragmentArtistListBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R

@AndroidEntryPoint
class ArtistListFragment : Fragment() {

    private var _binding: FragmentArtistListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistListViewModel by viewModels()
    private lateinit var adapter: ArtistListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ArtistListAdapter(mutableListOf(),
            onHeartClick = { artistId ->
                viewModel.toggleLike(artistId)
            },
            onItemClick = { artist ->
                val action = ArtistListFragmentDirections
                    .actionArtistListFragmentToArtistDetailFragment(artist)
                findNavController().navigate(action)
            }
        )

        binding.rvArtistLists.layoutManager = LinearLayoutManager(requireContext())
        binding.rvArtistLists.adapter = adapter

        // 구분선 추가
        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.rvArtistLists.addItemDecoration(divider)

        viewModel.artists.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


