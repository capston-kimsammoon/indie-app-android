package com.kimthreemun.indieconcertapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.navigation.fragment.findNavController
import com.kimthreemun.indieconcertapp.databinding.FragmentHomeBinding
import com.kimthreemun.indieconcert.ui.home.adapter.NewConcertAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.RecommendedArtistAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.TicketOpenAdapter
import com.kimthreemun.indieconcert.ui.home.adapter.WeeklyConcertAdapter
import com.kimthreemun.indieconcertapp.R
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var weeklyAdapter: WeeklyConcertAdapter
    private lateinit var newAdapter: NewConcertAdapter
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
        super.onViewCreated(view, savedInstanceState)

        println("✅ HomeFragment onViewCreated 호출됨")

        setupDateText()
        setupClickListeners()
        setupAdapters()

        viewModel.concerts.observe(viewLifecycleOwner) { list ->
            println("🎯 concerts 데이터 개수: ${list.size}")
            weeklyAdapter.submitList(list)
            newAdapter.submitList(list)
            recommendedAdapter.submitList(list)
        }

        viewModel.ticketOpenConcerts.observe(viewLifecycleOwner) { list ->
            println("🎯 ticketOpenConcerts 데이터 개수: ${list.size}")
            ticketAdapter.submitList(list)
        }
    }

    private fun setupDateText() {
        val sdf = SimpleDateFormat("M월 d일", Locale.getDefault())
        val today = Calendar.getInstance().time
        binding.tvTodayTitle.text = "${sdf.format(today)} 공연"
    }

    private fun setupClickListeners() {
        // ✅ 사이드 메뉴 열기
        binding.iconHamburger.setOnClickListener {
            val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
            drawer.openDrawer(GravityCompat.START)
        }

        binding.iconQ.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
        }

        binding.layoutCalendar.setOnClickListener {
            findNavController().navigate(R.id.calendarFragment)
        }
    }

    private fun setupAdapters() {
        weeklyAdapter = WeeklyConcertAdapter()
        newAdapter = NewConcertAdapter()
        ticketAdapter = TicketOpenAdapter()
        recommendedAdapter = RecommendedArtistAdapter()

        binding.viewPagerTodayConcert.apply {
            adapter = weeklyAdapter
            offscreenPageLimit = 1
        }

        binding.recyclerNewConcert.apply {
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

        binding.dotsIndicator.attachTo(binding.viewPagerTodayConcert)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
