package com.kimthreemun.indieconcertapp.ui.community.create


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Post
import com.kimthreemun.indieconcertapp.ui.community.list.DummyPostData


class PostWriteFragment : Fragment() {

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_write, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        editTitle = view.findViewById(R.id.edit_title)
        editContent = view.findViewById(R.id.edit_content)
        submitButton = view.findViewById(R.id.btn_submit)
        backButton = view.findViewById(R.id.btn_back)

        submitButton.setOnClickListener {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()

            if (title.isBlank() || content.isBlank()) {
                Toast.makeText(requireContext(), "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 임시로 게시물 ID 생성 (마지막 ID + 1)
            val newId = (DummyPostData.postList.maxOfOrNull { it.id } ?: 0) + 1

            val newPost = Post(
                id = newId,
                nickname = "나", // 임시 닉네임
                profileUrl = "",
                createdAt = "방금 전",
                title = title,
                content = content,
                imageUrl = null,
                commentCount = 0
            )

            DummyPostData.postList = listOf(newPost) + DummyPostData.postList // 맨 앞에 추가

            Toast.makeText(requireContext(), "게시물이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack() // 게시판으로 돌아감
        }

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}