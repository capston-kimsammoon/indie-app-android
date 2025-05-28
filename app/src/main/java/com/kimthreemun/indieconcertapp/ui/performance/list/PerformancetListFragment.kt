package com.kimthreemun.indieconcertapp.ui.performance.list

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
import com.kimthreemun.indieconcertapp.common.util.showOptionBottomSheet
import com.kimthreemun.indieconcertapp.databinding.FragmentPerformanceListBinding
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PerformanceListFragment : Fragment() {

    private var _binding: FragmentPerformanceListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PerformanceListViewModel by viewModels()
    private lateinit var adapter: PerformanceListAdapter

    private var selectedSortOption: String? = null
    private var selectedRegions: List<String> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformanceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "공연")

        adapter = PerformanceListAdapter(emptyList()) { performance ->
            val action = PerformanceListFragmentDirections
                .actionPerformanceListFragmentToPerformanceDetailFragment(performance)
            findNavController().navigate(action)
        }


        binding.rvPerformances.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPerformances.adapter = adapter

        val divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            divider.setDrawable(it)
        }
        binding.rvPerformances.addItemDecoration(divider)

        viewModel.performances.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        binding.ivCalendar.setOnClickListener {
             findNavController().navigate(R.id.calendarFragment)
        }

        binding.btnSort.setOnClickListener { showSortOptions() }
        binding.btnRegion.setOnClickListener { showRegionOptions() }
    }

    private fun showSortOptions() {
        val options = listOf("최근등록순", "공연임박순", "인기많은순")
        showOptionBottomSheet(
            title = "정렬 선택",
            options = options,
            isMultiSelect = false,
            preSelected = listOfNotNull(selectedSortOption).toSet()
        ) { selected ->
            selected.firstOrNull()?.let {
                selectedSortOption = it
                binding.btnSort.text = it
                viewModel.sortBy(it)
            }
        }
    }

    private fun showRegionOptions() {
        val regions = listOf("전체", "서울", "경기", "인천", "부산", "대구", "광주", "대전", "울산", "세종", "강원", "충청", "전라", "경상", "제주")

        showOptionBottomSheet(
            title = "지역 선택",
            options = regions,
            isMultiSelect = true,
            preSelected = selectedRegions.toSet()
        ) { selected ->
            selectedRegions = selected
            val text = if (selected.isEmpty()) "지역" else "지역 ${selected.joinToString(", ")}"
            binding.btnRegion.text = text
            viewModel.filterByRegions(selected)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
