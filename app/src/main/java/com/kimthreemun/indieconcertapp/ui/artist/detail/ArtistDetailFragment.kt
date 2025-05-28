package com.kimthreemun.indieconcertapp.ui.artist.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentArtistDetailBinding
import com.kimthreemun.indieconcertapp.ui.artist.detail.ArtistDetailAdapter
import com.kimthreemun.indieconcertapp.ui.performance.detail.PerformanceDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistDetailViewModel by viewModels()

    private lateinit var scheduledAdapter: ArtistDetailAdapter
    private lateinit var pastAdapter: ArtistDetailAdapter

    private val args: ArtistDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val artist = args.artist

        setupRecyclerViews()
        setupListeners()
        observeViewModel()

        viewModel.setArtist(artist)
    }

    private fun setupRecyclerViews() {
        scheduledAdapter = ArtistDetailAdapter(mutableListOf()) { performance ->
            val action = ArtistDetailFragmentDirections
                .actionArtistDetailFragmentToPerformanceDetailFragment(performance)
            findNavController().navigate(action)
        }

        pastAdapter = ArtistDetailAdapter(mutableListOf()) { performance ->
            val action = ArtistDetailFragmentDirections
                .actionArtistDetailFragmentToPerformanceDetailFragment(performance)
            findNavController().navigate(action)
        }

        binding.rvScheduledPerformances.apply {
            adapter = scheduledAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        binding.rvPastPerformances.apply {
            adapter = pastAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }


    private fun setupListeners() {
        binding.ivHeart.setOnClickListener {
            viewModel.toggleLike()
        }

        binding.btnNotify.setOnClickListener {
            viewModel.toggleNotify()
        }
    }

    private fun observeViewModel() {
        viewModel.artist.observe(viewLifecycleOwner) { artist ->
            binding.tvArtistName.text = artist.name
            binding.tvInstagram.text = artist.instagramHandle

            Glide.with(this)
                .load(artist.profileImageUrl.ifBlank { R.drawable.sample_profile })
                .placeholder(R.drawable.sample_profile)
                .circleCrop()
                .into(binding.ivProfile)
        }

        viewModel.isLiked.observe(viewLifecycleOwner) { liked ->
            binding.ivHeart.setImageResource(
                if (liked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
        }

        viewModel.isNotified.observe(viewLifecycleOwner) { notified ->
            binding.btnNotify.apply {
                setImageResource(
                    if (notified) R.drawable.ic_notify_on else R.drawable.ic_notify_off
                )
            }
        }


        viewModel.scheduledPerformances.observe(viewLifecycleOwner) {
            scheduledAdapter.setData(it)
        }

        viewModel.pastPerformances.observe(viewLifecycleOwner) {
            pastAdapter.setData(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
