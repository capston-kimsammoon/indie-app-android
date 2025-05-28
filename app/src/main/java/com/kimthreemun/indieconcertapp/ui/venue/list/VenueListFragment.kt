package com.kimthreemun.indieconcertapp.ui.venue.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue
import com.kimthreemun.indieconcertapp.ui.venue.list.VenueListAdapter
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader

class VenueListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_venue_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "공연장")

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_venues)

        // 샘플 데이터
        val venues = listOf(
            Venue(id = 1, name = "언플러그드 홍대점", profileImageResId = R.drawable.venue_profile1),
            Venue(id = 2, name = "우주정거장 (SoaceStation)", profileImageResId = R.drawable.venue_profile2),
            Venue(id = 3, name = "BENDER LIVEHOUSE", profileImageResId = R.drawable.venue_profile3),
            Venue(id = 4, name = "부드러운직선", profileImageResId = R.drawable.venue_profile4),
            Venue(id = 5, name = "공상온도", profileImageResId = R.drawable.venue_profile5),
            Venue(id = 6, name = "CLUB STEEL FACE (CLUB SF)", profileImageResId = R.drawable.venue_profile6),
            Venue(id = 7, name = "생기스튜디오", profileImageResId = R.drawable.venue_profile7),
            Venue(id = 8, name = "베이비돌 ", profileImageResId = R.drawable.venue_profile8),
            Venue(id = 9, name = "클럽샤프", profileImageResId = R.drawable.venue_profile9),
            Venue(id = 10, name = "인터플레이", profileImageResId = R.drawable.venue_profile10),
            Venue(id = 11, name = "고요의 방", profileImageResId = R.drawable.venue_profile11),
            Venue(id = 12, name = "언플러그드 라운지", profileImageResId = R.drawable.venue_profile12),
            Venue(id = 13, name = "채널1969", profileImageResId = R.drawable.venue_profile13),
            Venue(id = 14, name = "모래내 극락", profileImageResId = R.drawable.venue_profile14),
            Venue(id = 15, name = "ASIA CRIME SAD", profileImageResId = R.drawable.venue_profile15),
            Venue(id = 16, name = "SUBRIOT HBC", profileImageResId = R.drawable.venue_profile16),
        )

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = VenueListAdapter(venues)
    }
}
