package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue

class VenueSearchFragment : Fragment(R.layout.fragment_venue_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText

    private val venueList = listOf(
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_venue_search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        etSearch = requireActivity().findViewById(R.id.et_search)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isBlank()) {
                    recyclerView.visibility = View.GONE
                } else {
                    val filtered = venueList.filter {
                        it.name.contains(query, ignoreCase = true)
                    }
                    recyclerView.adapter = VenueAdapter(filtered)
                    recyclerView.visibility = if (filtered.isEmpty()) View.GONE else View.VISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
