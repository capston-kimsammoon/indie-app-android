package com.kimthreemun.indieconcertapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.kimthreemun.indieconcertapp.databinding.FragmentHomeBinding
import com.kimthreemun.indieconcert.ui.home.adapter.NewPerformanceAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.RecommendedArtistAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.TicketOpenAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.WeeklyPerformanceAdapter
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader

import com.kimthreemun.indieconcertapp.R
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var weeklyAdapter: WeeklyPerformanceAdapter
    private lateinit var newAdapter: NewPerformanceAdapter
    private lateinit var ticketAdapter: TicketOpenAdapter
    private lateinit var recommendedAdapter: RecommendedArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "", showBack = false)

        super.onViewCreated(view, savedInstanceState)

        setupDateText()
        setupClickListeners()
        setupAdapters()

        viewModel.performances.observe(viewLifecycleOwner) { list ->
            weeklyAdapter.submitList(list)
            newAdapter.submitList(list)
            recommendedAdapter.submitList(list)
        }

        viewModel.ticketOpenPerformances.observe(viewLifecycleOwner) { list ->
            ticketAdapter.submitList(list)
        }
    }

    private fun setupDateText() {
        val sdf = SimpleDateFormat("M월 d일", Locale.getDefault())
        val today = Calendar.getInstance().time
        binding.tvTodayTitle.text = "${sdf.format(today)} 공연"
    }

    private fun setupClickListeners() {
        binding.layoutCalendar.setOnClickListener {
            findNavController().navigate(R.id.calendarFragment)
        }
    }

    private fun setupAdapters() {
        weeklyAdapter = WeeklyPerformanceAdapter(
            onGoClick = {
                val current = binding.viewPagerTodayPerformance.currentItem
                val total = weeklyAdapter.itemCount
                if (current < total - 1) {
                    binding.viewPagerTodayPerformance.currentItem = current + 1
                }
            },
            onItemClick = { performance ->
                val action = HomeFragmentDirections
                    .actionHomeFragmentToPerformanceDetailFragment(performance)
                findNavController().navigate(action)
            }
        )
        newAdapter = NewPerformanceAdapter(
            onItemClick = { performance ->
                val action = HomeFragmentDirections.actionHomeFragmentToPerformanceDetailFragment(performance)
                findNavController().navigate(action)
            }
        )
        ticketAdapter = TicketOpenAdapter(
            onItemClick = { performance ->
                val action = HomeFragmentDirections.actionHomeFragmentToPerformanceDetailFragment(performance)
                findNavController().navigate(action)
            }
        )
        recommendedAdapter = RecommendedArtistAdapter(
            onItemClick = { performance ->
                val action = HomeFragmentDirections.actionHomeFragmentToPerformanceDetailFragment(performance)
                findNavController().navigate(action)
            }
        )

        binding.viewPagerTodayPerformance.apply {
            adapter = weeklyAdapter
            offscreenPageLimit = 1
        }

        binding.recyclerNewPerformance.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = newAdapter
            setHasFixedSize(true)
            itemAnimator = null
        }

        binding.recyclerTicketOpen.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ticketAdapter
            setHasFixedSize(true)
            itemAnimator = null
        }

        binding.recyclerRecommended.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendedAdapter
            setHasFixedSize(true)
            itemAnimator = null
        }

        binding.dotsIndicator.attachTo(binding.viewPagerTodayPerformance)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
