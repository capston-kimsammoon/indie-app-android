package com.kimthreemun.indieconcertapp.ui.community.list


import com.kimthreemun.indieconcertapp.data.model.domain.Comment

object DummyCommentData {
    val commentList = mutableListOf(
        // Post 1
        Comment(
            id = 1,
            postId = 1,
            nickname = "너만인디좋아하냐나도좋아한다",
            profileUrl = "",
            content = "헉 저랑 동행하실래요?",
            createdAt = "19:20"
        ),
        Comment(
            id = 2,
            postId = 1,
            nickname = "나락도락이다",
            profileUrl = "",
            content = "라인업 누군데용",
            createdAt = "20:15"
        ),

        // Post 2
        Comment(
            id = 3,
            postId = 2,
            nickname = "메탈이",
            profileUrl = "",
            content = "홍대면 엎어지면 코닿는 거리에 공연장 다 있자나여",
            createdAt = "14:19"
        ),

        // Post 3
        Comment(
            id = 4,
            postId = 3,
            nickname = "가똥이",
            profileUrl = "",
            content = "오방가르드 음향 ㄱㅊ나요",
            createdAt = "5/11"
        ),
        // Post 3
        Comment(
            id = 5,
            postId = 3,
            nickname = "로로야사랑해",
            profileUrl = "",
            content = "ㅇㅇ꽤괜",
            createdAt = "5/11",
            parentCommentId = 4
        ),

        // Post 4
        Comment(
            id = 6,
            postId = 4,
            nickname = "아집가고싶다",
            profileUrl = "",
            content = "홍대 공연장 웬만하면 주차장 없습니다 참고하세요",
            createdAt = "3분 전",
            parentCommentId = 3
        ),

        // Post 5
        Comment(
            id = 7,
            postId = 5,
            nickname = "틱택톡",
            profileUrl = "",
            content = "밴드 향이라고 있는데 summer 강추요 시작할 때 드럼 개존맛",
            createdAt = "05/10",
        ),
        Comment(
            id = 8,
            postId = 5,
            nickname = "인디밖에모르는바보",
            profileUrl = "",
            content = "새소년이랑 비슷한 깔..은 잘 모르겠고 모스크바서핑클럽 ㄱㄱㄱㄱ",
            createdAt = "9분 전",
        ),

        // Post 6
        Comment(
            id = 9,
            postId = 6,
            nickname = "디깅리리링깅",
            profileUrl = "",
            content = "감튀에 마요네즈 극락 조합임 ",
            createdAt = "05/10",
        ),
        Comment(
            id = 10,
            postId = 6,
            nickname = "내가김가희다",
            profileUrl = "",
            content = "케찹이지 뭔 마요네즈..",
            createdAt = "11분 전",
            parentCommentId = 9
        )


    )
}