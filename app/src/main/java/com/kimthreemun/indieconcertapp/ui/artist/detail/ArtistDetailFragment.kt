package com.kimthreemun.indieconcertapp.ui.artist.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimthreemun.indieconcertapp.R

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.databinding.FragmentArtistDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.ui.artist.detail.ArtistDetailViewModel
import com.kimthreemun.indieconcertapp.ui.artist.detail.ArtistDetailAdapter

@AndroidEntryPoint
class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ArtistDetailViewModel by viewModels()

    private lateinit var scheduledAdapter: ArtistDetailAdapter
    private lateinit var pastAdapter: ArtistDetailAdapter

    private var isLiked = false
    private var isNotified = false

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

        scheduledAdapter = ArtistDetailAdapter(mutableListOf())
        pastAdapter = ArtistDetailAdapter(mutableListOf())

        binding.rvScheduledPerformances.adapter = scheduledAdapter
        binding.rvScheduledPerformances.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.rvPastPerformances.adapter = pastAdapter
        binding.rvPastPerformances.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        //setupListeners()
        //observeViewModel()

        //val artistId = arguments?.getInt("artistId") ?: return
        //viewModel.loadArtistDetail(artistId)

        val testScheduledPerformances = listOf(
            Performance(id = 1, title = "내일의공연입니따~", date = "2025.05.23", posterImageResId = R.drawable.sample_poster),
        )

        val testPastPerformances = listOf(
            Performance(id = 1, title = "어제의공연입니따~", date = "2025.05.21", posterImageResId = R.drawable.sample_poster),
        )
        scheduledAdapter.setData(testScheduledPerformances)
        pastAdapter.setData(testPastPerformances)
    }

    private fun setupListeners() {
        binding.btnLike.setOnClickListener {
            isLiked = !isLiked
            updateLikeButton()
            viewModel.toggleLike()
        }

        binding.btnNotify.setOnClickListener {
            isNotified = !isNotified
            updateNotifyButton()
            viewModel.toggleNotify()
        }
    }

    private fun updateLikeButton() {
        binding.btnLike.setImageResource(
            if (isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
        )
    }

    private fun updateNotifyButton() {
        val drawable = if (isNotified) R.drawable.ic_notify_on else R.drawable.ic_notify_off
        binding.btnNotify.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
