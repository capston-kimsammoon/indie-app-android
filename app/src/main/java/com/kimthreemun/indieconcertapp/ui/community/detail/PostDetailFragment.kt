package com.kimthreemun.indieconcertapp.ui.community.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Comment
import com.kimthreemun.indieconcertapp.data.model.domain.Post
import com.kimthreemun.indieconcertapp.ui.community.list.CommentAdapter
import com.kimthreemun.indieconcertapp.ui.community.list.DummyCommentData
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostDetailFragment : Fragment() {

    private var post: Post? = null
    private lateinit var commentAdapter: CommentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        post = arguments?.getParcelable("post")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        SetupCommonHeader(view, title = "자유게시판")

        super.onViewCreated(view, savedInstanceState)

        // 제목 변경
        view.findViewById<TextView>(R.id.headerTitle).text = "자유게시판"

        // 뒤로가기 버튼 동작 설정
        view.findViewById<ImageView>(R.id.iconBack).setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        post?.let { p ->
            view.findViewById<TextView>(R.id.detailTitle).text = p.title
            view.findViewById<TextView>(R.id.detailContent).text = p.content
            view.findViewById<TextView>(R.id.detailInfo).text =
                "${p.nickname} · ${p.createdAt}"

            val imageView = view.findViewById<ImageView>(R.id.detailImage)
            if (!p.imageUrl.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
                Glide.with(this).load(p.imageUrl).into(imageView)
            } else {
                imageView.visibility = View.GONE
            }

            // 댓글 처리
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewComments)
            commentAdapter = CommentAdapter()
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = commentAdapter

            val relatedComments = DummyCommentData.commentList.filter { it.postId == p.id }
            commentAdapter.submitList(relatedComments)

            // ✅ 댓글 등록 기능
            val commentEdit = view.findViewById<EditText>(R.id.edit_comment)
            val sendButton = view.findViewById<ImageButton>(R.id.btn_send_comment)

            sendButton.setOnClickListener {
                val text = commentEdit.text.toString()
                if (text.isBlank()) {
                    Toast.makeText(requireContext(), "댓글을 입력하세요", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val newCommentId = (DummyCommentData.commentList.maxOfOrNull { it.id } ?: 0) + 1
                val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                val now = formatter.format(Date())

                val newComment = Comment(
                    id = newCommentId,
                    postId = p.id,
                    nickname = "나",
                    profileUrl = "",
                    content = text,
                    createdAt = now
                )

                DummyCommentData.commentList.add(newComment)
                commentEdit.setText("")

                val updatedComments = DummyCommentData.commentList.filter { it.postId == p.id }
                commentAdapter.submitList(updatedComments)
            }
        }
    }

    companion object {
        fun newInstance(post: Post): PostDetailFragment {
            val fragment = PostDetailFragment()
            val bundle = Bundle()
            bundle.putParcelable("post", post)
            fragment.arguments = bundle
            return fragment
        }
    }


}