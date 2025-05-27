package com.kimthreemun.indieconcertapp.ui.community.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Post


class BoardFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_board_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewPosts)

        postAdapter = PostAdapter { post ->
            // ✅ 게시물 클릭 시 상세페이지로 이동
            val action = BoardFragmentDirections.actionPostListFragmentToPostDetailFragment(post)
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = postAdapter

        postAdapter.submitList(DummyPostData.postList)

        view.findViewById<Button>(R.id.btn_write_post).setOnClickListener {
            val action = BoardFragmentDirections.actionPostListFragmentToPostWriteFragment()
            findNavController().navigate(action)
        }
    }

}