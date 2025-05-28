package com.kimthreemun.indieconcertapp.ui.venue.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kimthreemun.indieconcertapp.R


import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.FragmentVenueDetailBinding
import com.kimthreemun.indieconcertapp.ui.venue.detail.VenueDetailViewModel
import com.kimthreemun.indieconcertapp.ui.venue.detail.VenueDetailAdapter


@AndroidEntryPoint
class VenueDetailFragment : Fragment() {


    private var _binding: FragmentVenueDetailBinding? = null
    private val binding get() = _binding!!


    private val viewModel: VenueDetailViewModel  by viewModels()


    private lateinit var scheduledAdapter: VenueDetailAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVenueDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        scheduledAdapter = VenueDetailAdapter(mutableListOf())


        binding.rvScheduledPerformances.adapter = scheduledAdapter
        binding.rvScheduledPerformances.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)


        //setupListeners()
        //observeViewModel()


        //val artistId = arguments?.getInt("artistId") ?: return
        //viewModel.loadArtistDetail(artistId)


        val testScheduledPerformances = listOf(
            Performance(id = 1, title = "SWELL", date = "2025.06.10", posterImageResId = R.drawable.venue_sample2),
            Performance(id = 2, title = "CLUB WED'S DAY", date = "2025.06.11", posterImageResId = R.drawable.venue_sample3),
            Performance(id = 3, title = "시선이닫힌세계", date = "2025.06.14", posterImageResId = R.drawable.venue_sample4),
        )


        scheduledAdapter.setData(testScheduledPerformances)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
