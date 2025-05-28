package com.kimthreemun.indieconcertapp.ui.community.list


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kimthreemun.indieconcertapp.data.model.dto.post.PostDto

class BoardViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<PostDto>>()
    val posts: LiveData<List<PostDto>> = _posts

    init {
        loadPosts()
    }

    fun loadPosts() {
        _posts.value = listOf(
            PostDto("1", "언플러그드 16일 공연 같이 가실 분", "16일에 언플러그드 같이 가실 분 계실까요?", "병일", "05/16", listOf()),
            PostDto("2", "홍대 공연장", "내일 친구랑 갈 건데 추천해주세요엽", "문수신아범", "05/11", listOf()),
            PostDto("3", "오방가르드 공연 후기", "어제 공연 보고 너무 만족했어요.", "인디블로오단", "05/11", listOf())
        )
    }
}
