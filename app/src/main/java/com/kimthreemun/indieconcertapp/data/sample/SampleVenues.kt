package com.kimthreemun.indieconcertapp.data.sample


import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue


object SampleVenues {


    val venue1 = Venue(
        id = 1,
        name = "언플러그드 홍대점",
        region = "서울",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile1
    )


    val venue2 = Venue(
        id = 2,
        name = "우주정거장 (SoaceStation)",
        region = "서울",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile2
    )


    val venue3 = Venue(
        id = 3,
        name = "BENDER LIVEHOUSE",
        region = "서울",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile3
    )


    val venue4 = Venue(
        id = 4,
        name = "부드러운직선",
        region = "강원",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile4
    )


    val venue5 = Venue(
        id = 5,
        name = "공상온도",
        region = "충청",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile5
    )


    val venue6 = Venue(
        id = 6,
        name = "CLUB STEEL FACE (CLUB SF)",
        region = "제주",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile6
    )


    val venue7 = Venue(
        id = 7,
        name = "생기스튜디오",
        region = "제주",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile7
    )


    val venue8 = Venue(
        id = 8,
        name = "베이비돌",
        region = "부산",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile8
    )


    val venue9 = Venue(
        id = 9,
        name = "클럽샤프",
        region = "부산",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile9
    )


    val venue10 = Venue(
        id = 10,
        name = "인터플레이",
        region = "전라",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile10
    )


    val venue11 = Venue(
        id = 11,
        name = "고요의 방",
        region = "충청",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile11
    )


    val venue12 = Venue(
        id = 12,
        name = "언플러그드 라운지",
        region = "인천",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile12
    )


    val venue13 = Venue(
        id = 13,
        name = "채널1969",
        region = "인천",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile13
    )


    val venue14 = Venue(
        id = 14,
        name = "모래내 극락",
        region = "인천",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile14
    )


    val venue15 = Venue(
        id = 15,
        name = "ASIA CRIME SAD",
        region = "울산",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile15
    )


    val venue16 = Venue(
        id = 16,
        name = "SUBRIOT HBC",
        region = "울산",
        scheduledPerformances = emptyList(),
        profileImageResId = R.drawable.venue_profile16
    )




    val all: List<Venue> = listOf(
        venue1, venue2, venue3, venue4, venue5, venue6, venue7, venue8, venue9,
        venue10, venue11, venue12, venue13, venue14, venue15, venue16
    )


}
