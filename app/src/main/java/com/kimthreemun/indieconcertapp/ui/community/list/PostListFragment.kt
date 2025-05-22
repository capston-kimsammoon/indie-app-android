package com.kimthreemun.indieconcertapp.ui.community.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R

class PostListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var boardAdapter: BoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // activity_board.xml을 inflate
        val view = inflater.inflate(R.layout.activity_board, container, false)

        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.recycler_posts)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // 더미 데이터 생성
        val dummyPosts = listOf(
            Post("언플러그드 16일 공연 같이 가실 분", "16일에 언플러그드 같이 가실 분 계실까요? 저는 김가희 팬입니다", 6, "19:14", "뻥임"),
            Post("혼자 공연 보러 가기 심심해요", "같이 공연 보실 분 구해요~", 2, "15:00", "가희찐팬"),
            Post("티켓 나눔합니다", "일정이 생겨서 티켓 한 장 드릴게요!", 1, "13:42", "익명")
        )

        // Adapter 연결
        boardAdapter = BoardAdapter(requireContext(), dummyPosts)
        recyclerView.adapter = boardAdapter

        return view
    }
}
