// /ui/performance/list/PerformanceListfragment.kt
// UI 레이아웃 및 로직
package com.kimthreemun.indieconcertapp.ui.performance.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentPerformanceListBinding
import dagger.hilt.android.AndroidEntryPoint

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomsheet.BottomSheetDialog
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.google.android.flexbox.FlexboxLayout

@AndroidEntryPoint
class PerformanceListFragment : Fragment() {

    private var _binding: FragmentPerformanceListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PerformanceListViewModel by viewModels()
    private lateinit var adapter: PerformanceListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformanceListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PerformanceListAdapter(emptyList()) { performance ->
            // 클릭 시 동작 추가
//            val action = PerformanceListFragmentDirections
//                .actionPerformanceListFragmentToPerformanceDetailFragment(performance.title)
//            findNavController().navigate(action)
        }

        binding.rvPerformances.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPerformances.adapter = adapter

        // 구분선 추가
        val dividerItemDecoration = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)?.let {
            dividerItemDecoration.setDrawable(it)
        }
        binding.rvPerformances.addItemDecoration(dividerItemDecoration)

        viewModel.performances.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        // 👇 클릭 리스너 설정
        binding.ivCalendar.setOnClickListener {
//            findNavController().navigate(R.id.calendarFragment)
        }

        // 정렬 버튼 클릭
        binding.btnSort.setOnClickListener {
            // 정렬 옵션 토글 또는 다이얼로그 표시
            showSortOptions()
        }

        // 지역 필터 버튼 클릭
        binding.btnRegion.setOnClickListener {
            // 지역 선택 다이얼로그 또는 드롭다운 표시
            showRegionOptions()
        }
    }

    private fun showBottomSheet(title: String, options: List<String>, onItemSelected: (String) -> Unit) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_option_list, null)

        val titleText = view.findViewById<TextView>(R.id.tvBottomSheetTitle)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvBottomSheetOptions)

        titleText.text = title
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = object : RecyclerView.Adapter<OptionViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
                return OptionViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
                val text = options[position]
                holder.textView.text = text
                holder.itemView.setOnClickListener {
                    onItemSelected(text)
                    bottomSheetDialog.dismiss()
                }
            }

            override fun getItemCount(): Int = options.size
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    class OptionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(android.R.id.text1)
    }


    private fun showSortOptions() {
        val options = listOf("최근등록순", "공연임박순", "인기많은순")
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_region_options, null) // 재사용 가능

        val container = view.findViewById<FlexboxLayout>(R.id.regionOptionContainer)
        val titleText = view.findViewById<TextView>(R.id.tvBottomSheetTitle)
        titleText.text = "정렬 방식 선택"

        options.forEach { option ->
            val chip = TextView(requireContext()).apply {
                text = option
                setPadding(32, 16, 32, 16)
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

                setOnClickListener {
                    // 모든 칩 초기화
                    for (i in 0 until container.childCount) {
                        val child = container.getChildAt(i) as TextView
                        child.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
                        child.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    }

                    background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_selected)
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    binding.btnSort.text = option
                    viewModel.sortBy(option)
                    bottomSheetDialog.dismiss()
                }
            }
            container.addView(chip)
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }


    private fun showRegionOptions() {
        val regions = listOf("전체", "서울", "경기", "인천", "부산", "대구", "광주", "대전", "울산", "세종", "강원", "충청", "전라", "경상", "제주")
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.bottom_sheet_region_options, null)

        val container = view.findViewById<FlexboxLayout>(R.id.regionOptionContainer)

        val selectedRegions = mutableSetOf<String>()  // 선택된 지역들

        regions.forEach { region ->
            val chip = TextView(requireContext()).apply {
                text = region
                setPadding(32, 16, 32, 16)
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.black))

                setOnClickListener {
                    if (selectedRegions.contains(region)) {
                        selectedRegions.remove(region)
                        background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_unselected)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                    } else {
                        selectedRegions.add(region)
                        background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_chip_selected)
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                    }

                    val displayText = if (selectedRegions.isEmpty()) "지역" else "지역 ${selectedRegions.joinToString(", ")}"
                    binding.btnRegion.text = displayText

                    viewModel.filterByRegions(selectedRegions.toList()) // <- ViewModel에서도 다중 필터 지원 필요
                }
            }
            container.addView(chip)
        }

        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
