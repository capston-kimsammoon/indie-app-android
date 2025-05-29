package com.kimthreemun.indieconcertapp.ui.venue.list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader
import com.kimthreemun.indieconcertapp.common.util.showOptionBottomSheet
import com.kimthreemun.indieconcertapp.ui.venue.list.VenueListViewModel
import com.kimthreemun.indieconcertapp.data.model.domain.Venue


class VenueListFragment : Fragment() {


    private lateinit var recyclerView: RecyclerView
    private lateinit var btnRegion: Button


    // ✅ 지역 선택값 저장
    private var selectedRegions: List<String> = emptyList()


    // ✅ ViewModel 연결
    private val viewModel: VenueListViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venue_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "공연장", showSearch = true)

        recyclerView = view.findViewById(R.id.rv_venues)
        btnRegion = view.findViewById(R.id.btnRegion)


        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        val adapter = VenueListAdapter()
        recyclerView.adapter = adapter


        viewModel.filteredVenues.observe(viewLifecycleOwner) { venues ->
            adapter.submitList(venues)
        }


        btnRegion.setOnClickListener {
            showRegionOptions()
        }


        viewModel.setOriginalVenues(getSampleVenues())
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
            btnRegion.text = text
            viewModel.filterByRegions(selected)
        }
    }


    private fun getSampleVenues(): List<Venue> {
        return listOf(
            Venue(id = 1, name = "언플러그드 홍대점", profileImageResId = R.drawable.venue_profile1, region = "서울"),
            Venue(id = 2, name = "우주정거장 (SoaceStation)", profileImageResId = R.drawable.venue_profile2, region = "서울"),
            Venue(id = 3, name = "BENDER LIVEHOUSE", profileImageResId = R.drawable.venue_profile3, region = "서울"),
            Venue(id = 4, name = "부드러운직선", profileImageResId = R.drawable.venue_profile4, region = "제주"),
            Venue(id = 5, name = "공상온도", profileImageResId = R.drawable.venue_profile5, region = "제주"),
            Venue(id = 6, name = "CLUB STEEL FACE (CLUB SF)", profileImageResId = R.drawable.venue_profile6, region = "충청"),
            Venue(id = 7, name = "생기스튜디오", profileImageResId = R.drawable.venue_profile7, region = "강원"),
            Venue(id = 8, name = "베이비돌 ", profileImageResId = R.drawable.venue_profile8, region = "강원"),
            Venue(id = 9, name = "클럽샤프", profileImageResId = R.drawable.venue_profile9, region = "제주"),
            Venue(id = 10, name = "인터플레이", profileImageResId = R.drawable.venue_profile10, region = "부산"),
            Venue(id = 11, name = "고요의 방", profileImageResId = R.drawable.venue_profile11, region = "부산"),
            Venue(id = 12, name = "언플러그드 라운지", profileImageResId = R.drawable.venue_profile12, region = "울산"),
            Venue(id = 13, name = "채널1969", profileImageResId = R.drawable.venue_profile13, region = "전라"),
            Venue(id = 14, name = "모래내 극락", profileImageResId = R.drawable.venue_profile14, region = "전라"),
            Venue(id = 15, name = "ASIA CRIME SAD", profileImageResId = R.drawable.venue_profile15, region = "대구"),
            Venue(id = 16, name = "SUBRIOT HBC", profileImageResId = R.drawable.venue_profile16, region = "대구"),
        )
    }
}
