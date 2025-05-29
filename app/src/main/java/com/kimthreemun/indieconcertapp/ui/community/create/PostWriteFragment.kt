package com.kimthreemun.indieconcertapp.ui.community.create

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Post
import com.kimthreemun.indieconcertapp.ui.community.list.DummyPostData
import java.text.SimpleDateFormat
import java.util.*

class PostWriteFragment : Fragment() {

    private lateinit var editTitle: EditText
    private lateinit var editContent: EditText
    private lateinit var submitButton: Button
    private lateinit var backButton: ImageView
    private lateinit var imagePreview: ImageView
    private lateinit var addImageButton: ImageView
    private var selectedImageUri: Uri? = null

    private val getImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            selectedImageUri = it
            imagePreview.setImageURI(it)
            imagePreview.visibility = View.VISIBLE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_post_write, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        view.findViewById<TextView>(R.id.headerTitle)?.text = "글쓰기"
        backButton = view.findViewById(R.id.btn_back)
        backButton.setOnClickListener { findNavController().popBackStack() }

        editTitle = view.findViewById(R.id.edit_title)
        editContent = view.findViewById(R.id.edit_content)
        submitButton = view.findViewById(R.id.btn_submit)
        imagePreview = view.findViewById(R.id.image_preview)
        addImageButton = view.findViewById(R.id.btn_add_image)

        addImageButton.setOnClickListener {
            getImage.launch("image/*")
        }

        submitButton.setOnClickListener {
            val title = editTitle.text.toString()
            val content = editContent.text.toString()

            if (title.isBlank() || content.isBlank()) {
                Toast.makeText(requireContext(), "제목과 내용을 모두 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val newId = (DummyPostData.postList.maxOfOrNull { it.id } ?: 0) + 1
            val now = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())

            val newPost = Post(
                id = newId,
                nickname = "나",
                profileUrl = "",
                createdAt = now,
                title = title,
                content = content,
                imageUrl = "", // 이미지 URI 저장
                commentCount = 0
            )

            DummyPostData.postList = listOf(newPost) + DummyPostData.postList
            Toast.makeText(requireContext(), "게시물이 등록되었습니다.", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }
}
