package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.ui.artist.list.ArtistListViewModel
import com.kimthreemun.indieconcertapp.ui.community.list.DummyPostData
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListViewModel
import com.kimthreemun.indieconcertapp.ui.search.ArtistAdapter
import com.kimthreemun.indieconcertapp.ui.search.BoardAdapter
import com.kimthreemun.indieconcertapp.ui.search.ShowAdapter

class SearchFragment : Fragment(R.layout.fragment_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvEmptyResult: TextView
    private lateinit var layoutRecentKeywords: LinearLayout
    private lateinit var etSearch: EditText

    private lateinit var tabShow: TextView
    private lateinit var tabVenue: TextView
    private lateinit var tabArtist: TextView
    private lateinit var tabBoard: TextView
    private lateinit var tabList: List<TextView>

    private lateinit var performanceViewModel: PerformanceListViewModel
    private lateinit var artistViewModel: ArtistListViewModel

    private var selectedTab: String = "공연"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        performanceViewModel =
            ViewModelProvider(requireActivity())[PerformanceListViewModel::class.java]
        artistViewModel =
            ViewModelProvider(requireActivity())[ArtistListViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.rv_search_results)
        tvEmptyResult = view.findViewById(R.id.tv_empty_result)
        layoutRecentKeywords = view.findViewById(R.id.layout_recent_keywords)
        etSearch = view.findViewById(R.id.et_search)

        tabShow = view.findViewById(R.id.tab_show)
        tabVenue = view.findViewById(R.id.tab_venue)
        tabArtist = view.findViewById(R.id.tab_artist)
        tabBoard = view.findViewById(R.id.tab_board)

        tabList = listOf(tabShow, tabVenue, tabArtist, tabBoard)

        // 탭 클릭 처리
        tabShow.setOnClickListener {
            selectedTab = "공연"
            selectTab(tabShow)

            performanceViewModel.performances.observe(viewLifecycleOwner) { list ->
                val filtered = list.filter { it.title.contains(etSearch.text.toString(), ignoreCase = true) }
                val adapter = ShowAdapter(filtered) // ✅ 리스트 넘기기
                setAdapterWithData(adapter, filtered.isEmpty())
            }
        }


        tabArtist.setOnClickListener {
            selectedTab = "아티스트"
            selectTab(tabArtist)
            selectTab(tabArtist)

            artistViewModel.artists.observe(viewLifecycleOwner) { list ->
                val filtered = list.filter { it.name.contains(etSearch.text.toString(), ignoreCase = true) }
                val adapter = ArtistAdapter(filtered) // ✅ 여기에 반드시 list 넘기기
                setAdapterWithData(adapter, filtered.isEmpty())
            }
        }

        tabBoard.setOnClickListener {
            selectedTab = "자유게시판"
            selectTab(tabBoard)

            val query = etSearch.text.toString()
            val filtered = DummyPostData.postList.filter {
                it.title.contains(query, ignoreCase = true) || it.content.contains(query, ignoreCase = true)
            }
            val adapter = BoardAdapter(filtered) // ✅ 리스트 넘기기
            setAdapterWithData(adapter, filtered.isEmpty())
        }


        tabVenue.setOnClickListener {
            selectedTab = "공연장"
            selectTab(tabVenue)
            // 공연장 기능 미구현 → 일단 숨김 처리
            setAdapterWithData(null, true)
        }

        // 검색어 입력 감지
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filterSearchQuery(s.toString().trim())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        val btnBack = view.findViewById<ImageView>(R.id.btn_back)
        btnBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        // 초기 상태
        tabShow.performClick()
    }


    private fun filterSearchQuery(query: String) {
        if (query.isBlank()) {
            recyclerView.visibility = View.GONE
            tvEmptyResult.visibility = View.GONE
            return
        }

        when (selectedTab) {
            "공연" -> {
                performanceViewModel.performances.observe(viewLifecycleOwner) { list ->
                    val filtered = list.filter { it.title.contains(query, ignoreCase = true) }
                    setAdapterWithData(ShowAdapter(filtered), filtered.isEmpty())
                }
            }

            "아티스트" -> {
                artistViewModel.artists.observe(viewLifecycleOwner) { list ->
                    val filtered = list.filter { it.name.contains(query, ignoreCase = true) }
                    setAdapterWithData(ArtistAdapter(filtered), filtered.isEmpty())
                }
            }

            "자유게시판" -> {
                val filtered = DummyPostData.postList.filter {
                    it.title.contains(query, ignoreCase = true) ||
                            it.content.contains(query, ignoreCase = true)
                }
                setAdapterWithData(BoardAdapter(filtered), filtered.isEmpty())
            }

            else -> {
                setAdapterWithData(null, true)
            }
        }
    }

    private fun selectTab(selectedTabView: TextView) {
        for (tab in tabList) {
            tab.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    if (tab == selectedTabView) R.color.tab_selected else R.color.tab_unselected
                )
            )
        }
    }

    private fun setAdapterWithData(adapter: RecyclerView.Adapter<*>?, isEmpty: Boolean) {
        recyclerView.adapter = adapter
        recyclerView.visibility = if (isEmpty) View.GONE else View.VISIBLE
        tvEmptyResult.visibility = if (isEmpty) View.VISIBLE else View.GONE
        layoutRecentKeywords.visibility = View.GONE
    }

}
