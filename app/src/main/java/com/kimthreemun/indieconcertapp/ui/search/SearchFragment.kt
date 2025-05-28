package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader

class SearchFragment : Fragment() {

    private lateinit var etSearch: EditText
    private lateinit var btnBack: ImageView
    private lateinit var layoutRecentKeywords: LinearLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "검색", showSearch = false)

        etSearch = view.findViewById(R.id.et_search)
//        btnBack = view.findViewById(R.id.btn_back)
        layoutRecentKeywords = view.findViewById(R.id.layout_recent_keywords)
        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.layout_tabs)

        // 탭 연결
        val adapter = SearchPagerAdapter(requireActivity())
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "공연"
                1 -> "아티스트"
                2 -> "자유게시판"
                else -> ""
            }
        }.attach()

        // 뒤로가기 동작
//        btnBack.setOnClickListener {
//            requireActivity().onBackPressedDispatcher.onBackPressed()
//        }

        // 검색어 입력 감지
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // ViewPager 내 Fragment들에게 검색어 전달 필요
                // 이 부분은 이후 연결 예정
                layoutRecentKeywords.visibility = if (s.isNullOrBlank()) View.VISIBLE else View.GONE
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        tabLayout.setTabTextColors(
            Color.parseColor("#4B4B4B"),  // 기본 회색
            Color.parseColor("#F14F21")   // 선택 시 주황
        )
    }
}