package com.kimthreemun.indieconcertapp.ui.calendar

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentCalendarBinding
import com.kimthreemun.indieconcertapp.ui.MainActivity
import com.kimthreemun.indieconcertapp.ui.calendar.adapter.DailyConcertAdapter
import com.kimthreemun.indieconcertapp.common.util.showOptionBottomSheet
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalendarViewModel by viewModels()
    private var selectedDate: LocalDate? = null
    private var currentMonth: YearMonth = YearMonth.now()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        setupCalendar()
        setupMonthNavigation()
        observeViewModel()
        setupHeaderButtons()
        setupRegionFilter()
    }

    private fun setupMonthNavigation() {
        binding.btnPrevMonth.setOnClickListener {
            currentMonth = currentMonth.minusMonths(1)
            updateCalendar()
        }

        binding.btnNextMonth.setOnClickListener {
            currentMonth = currentMonth.plusMonths(1)
            updateCalendar()
        }
    }

    private fun setupHeaderButtons() {
        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.btnHamburger.setOnClickListener {
            (activity as? MainActivity)?.openDrawer()
        }

        binding.btnHelp.setOnClickListener {
            findNavController().navigate(R.id.action_calendarFragment_to_searchFragment)
        }
    }

    private fun setupRegionFilter() {
        binding.layoutLocationFilter.setOnClickListener {
            val regions = listOf("전체", "서울", "경기", "인천", "부산", "대구", "광주", "대전", "울산", "세종", "강원", "충청", "전라", "경상", "제주")

            showOptionBottomSheet(
                title = "지역 선택",
                options = regions,
                isMultiSelect = true,
                preSelected = viewModel.selectedRegions.value ?: emptySet()
            ) { selected ->
                val filtered = if (selected.contains("전체")) emptySet() else selected.toSet()
                viewModel.updateRegionFilter(filtered)

                binding.tvLocationFilter.text =
                    if (filtered.isEmpty()) "지역 전체" else filtered.joinToString(", ")

                binding.calendarView.notifyCalendarChanged() // ✅ 지역 필터 변경 후 갱신
            }
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerDailyConcerts.apply {
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = DailyConcertAdapter(emptyList())
            addItemDecoration(GridSpacingItemDecoration(3, 16, true))
        }
    }

    private fun setupCalendar() {
        val daysOfWeek = daysOfWeek()

        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.text = day.date.dayOfMonth.toString()

                if (day.owner == DayOwner.THIS_MONTH) {
                    container.textView.visibility = View.VISIBLE
                    val date = day.date

                    val concertDates = viewModel.getConcertDates() // ✅ 이 부분 수정

                    when {
                        selectedDate == date -> {
                            container.textView.setBackgroundResource(R.drawable.bg_day_selected)
                            container.textView.setTextColor(resources.getColor(R.color.white, null))
                        }
                        concertDates.contains(date) -> {
                            container.textView.setBackgroundResource(R.drawable.bg_day_with_concert)
                            container.textView.setTextColor(resources.getColor(R.color.text_primary, null))
                        }
                        else -> {
                            container.textView.background = null
                            container.textView.setTextColor(resources.getColor(R.color.text_hint, null))
                        }
                    }

                    container.view.setOnClickListener {
                        selectedDate = date
                        viewModel.selectDate(date)
                        binding.calendarView.notifyCalendarChanged()
                    }
                } else {
                    container.textView.visibility = View.INVISIBLE
                }
            }
        }

        updateCalendar()
    }

    private fun updateCalendar() {
        val daysOfWeek = daysOfWeek()
        binding.calendarView.setup(currentMonth, currentMonth, daysOfWeek.first())
        binding.calendarView.scrollToMonth(currentMonth)

        val formatter = DateTimeFormatter.ofPattern("M월")
        binding.tvCurrentMonth.text = currentMonth.format(formatter)
    }

    private fun observeViewModel() {
        viewModel.selectedDate.observe(viewLifecycleOwner) { date ->
            date?.let {
                val formatter = DateTimeFormatter.ofPattern("M월 d일 EEEE 공연", java.util.Locale.KOREAN)
                binding.tvSelectedDate.text = it.format(formatter)
                binding.tvSelectedDate.isVisible = true
            }
        }

        viewModel.filteredConcerts.observe(viewLifecycleOwner) { concerts ->
            binding.recyclerDailyConcerts.adapter = DailyConcertAdapter(concerts)
            binding.calendarView.notifyCalendarChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView: TextView = view.findViewById(R.id.textViewDay)
    }

    private class GridSpacingItemDecoration(
        private val spanCount: Int,
        private val spacing: Int,
        private val includeEdge: Boolean
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % spanCount

            if (includeEdge) {
                outRect.left = spacing * (spanCount - column) / spanCount
                outRect.right = spacing * (column + 1) / spanCount
                if (position < spanCount) outRect.top = spacing
                outRect.bottom = spacing
            } else {
                outRect.left = spacing * column / spanCount
                outRect.right = spacing * (spanCount - 1 - column) / spanCount
                if (position >= spanCount) outRect.top = spacing
            }
        }
    }

    private fun daysOfWeek(): List<DayOfWeek> {
        return listOf(
            DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY,
            DayOfWeek.SUNDAY
        )
    }
}
