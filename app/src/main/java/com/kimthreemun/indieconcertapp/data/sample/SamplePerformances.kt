// data/sample/SamplePerformances.kt
package com.kimthreemun.indieconcertapp.data.sample

import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.data.sample.SampleArtists

object SamplePerformances {
    lateinit var artist1: Artist
    lateinit var artist2: Artist
    lateinit var artist3: Artist
    lateinit var artist4: Artist
    lateinit var artist5: Artist
    lateinit var artist6: Artist
    lateinit var artist7: Artist
    lateinit var artist9: Artist
    lateinit var artist10: Artist
    lateinit var artist11: Artist
    lateinit var artist12: Artist
    lateinit var artist13: Artist

    val performance1 = Performance(
        id = 1,
        title = "<이제(SOON)> ‘우리가 그리는‘",
        venue = "톤스튜디오 서울",
        date = "2025.06.21. 토요일",
        time = "15:00/19:00",
        region = "서울",
        price = "66,000",
        ticketOpenDate = "2025.06.04. 수요일",
        ticketOpenTime = "20:00",
        isLiked = true,
        detailLink = "tonestudio_live",
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/501266799_17912882760129853_4919595639289892793_n.jpg?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkZFRUQuaW1hZ2VfdXJsZ2VuLjEzNTB4MTY4Ny5zZHIuZjc1NzYxLmRlZmF1bHRfaW1hZ2UifQ&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_oc=Q6cZ2QFpkXJy9pSXk3nu6azykzI_YzcTRxM0OXYGjgDx81Z5HYsbejfq_g94-Y4pMqMk-Is&_nc_ohc=c_MekKJOJ_gQ7kNvwEJkyoz&_nc_gid=l3gHLtPMhY__SJnixpRHtg&edm=AA5fTDYBAAAA&ccb=7-5&ig_cache_key=MzY0MjE3NTc3ODc4OTc3NTgzOA%3D%3D.3-ccb7-5&oh=00_AfJb0a7Y_fD1u4E-l4KCNRWbf8eZfS3N6Fa2e0cX3o4Vwg&oe=683C44E4&_nc_sid=7edfe2",
    )
    val performance2 = Performance(
        id = 2,
        title = "SUMMER MATCH",
        venue = "홍대 언플러그드 B1 공연장",
        date = "2025.06.15 일요일",
        time = "19:00",
        region = "서울",
        price = "25,000",
        isLiked = true,
        ticketOpenDate = "2025.06.02 월요일",
        ticketOpenTime = "19:00",
        detailLink = "unplugged_stage",
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/502095149_18016029245718695_3203120018360874196_n.jpg?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkZFRUQuaW1hZ2VfdXJsZ2VuLjEzNTB4MTY4OC5zZHIuZjc1NzYxLmRlZmF1bHRfaW1hZ2UifQ&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_oc=Q6cZ2QGTi2Xb3_KYaEsK0woqkTL-ATWqBRG34NOrl1wIbAPPFAEZdRGE_WbBW3nBawZphEw&_nc_ohc=YZZwqEr2HygQ7kNvwH7te9P&_nc_gid=SpJiQ4ecZF7DT-IfY6vLYQ&edm=AP4sbd4BAAAA&ccb=7-5&ig_cache_key=MzY0MTUxMTcwMzQ4NzMxODE4OA%3D%3D.3-ccb7-5&oh=00_AfI0iyRueUWbTAoW_2UJEBb1Z7IIKZS6ZCxtPZp04eaiAA&oe=683C4BB0&_nc_sid=7a9f4b"
    )
    val performance3 = Performance(
        id = 3,
        title = "Plane On Mars – Undefined Planet Korea Tour",
        date = "2025.06.15 일요일",
        time = "19:00",
        venue = "거제대로 3734, 지하 1층 언드",
        region = "경상",
        detailLink = "und_space",
        isLiked = true,
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/500308900_18003168983780951_5923566610286458903_n.jpg?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkNBUk9VU0VMX0lURU0uaW1hZ2VfdXJsZ2VuLjE0NDB4MTgwMC5zZHIuZjc1NzYxLmRlZmF1bHRfaW1hZ2UifQ&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=111&_nc_oc=Q6cZ2QHtOFOt6ucWlh5R65SaIxUuDwB9Jno-4D78pSoEKi8E004LsI-PLkxM_zAt1j4C-sY&_nc_ohc=DqMC0cc26oQQ7kNvwEJDZRj&_nc_gid=pX_VJxKOwiGWTqOTUkOiwA&edm=AP4sbd4BAAAA&ccb=7-5&ig_cache_key=MzYzOTk4NzI4OTY5NjQ1MjE5MA%3D%3D.3-ccb7-5&oh=00_AfJdOR-yZlGQOrBYF1pQuItnHx-kiLs1Lbyj6ikqxDjsbQ&oe=683C3C5A&_nc_sid=7a9f4b"
    )
    val performance4 = Performance(
        id = 4,
        title = "[KOONG WORLD와 함께하는 우주라이브] '밤하늘'",
        date = "2025.06.15 일요일",
        time = "19:00",
        venue = "홍대우주정거장",
        region = "서울",
        detailLink = "spacestation2017",
        isLiked = true,
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t51.2885-15/496659538_18352465843199360_4775088336706842684_n.jpg?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkZFRUQuaW1hZ2VfdXJsZ2VuLjEwODB4MTA4MC5zZHIuZjc1NzYxLmRlZmF1bHRfaW1hZ2UifQ&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=108&_nc_oc=Q6cZ2QFPs1XsrNixfpwWms6f7PGeROPpEQMbq3ntbj-Yg1fk75TU8StLK3-Lgv7R5TORqxg&_nc_ohc=4y6979mC0IIQ7kNvwFLc8MF&_nc_gid=0PYyxNpnQqb52qvcFGs7nw&edm=AP4sbd4BAAAA&ccb=7-5&ig_cache_key=MzYyOTIzOTk3NzU2MDcwMzE5Mg%3D%3D.3-ccb7-5&oh=00_AfJXx38L8OP-biQ8WzZ7EBGgScklg8ulsIeV1ERp47AyRw&oe=683C707A&_nc_sid=7a9f4b"
    )
    val performance5 = Performance(
        id = 5,
        title = "CALLING AFTER ME@FF",
        date = "2025.06.15 일요일",
        time = "19:00",
        region = "서울",
        detailLink = "hongdaeff",
        isLiked = false,
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t51.29350-15/501313206_1740979923438517_8144763971432350307_n.heic?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkZFRUQuaW1hZ2VfdXJsZ2VuLjE0NDB4MTc5Mi5zZHIuZjI5MzUwLmRlZmF1bHRfaW1hZ2UifQ&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=101&_nc_oc=Q6cZ2QGd1D6zRSZJRSzgZlmYw98t1WgP8g3lKBgOEROFGxNxbLB-ELmn_8ZYwBhTCW1206o&_nc_ohc=q3Dvw-mXu8gQ7kNvwHnFyxX&_nc_gid=H-zugpaYPZwkR_PWDBG22g&edm=AP4sbd4BAAAA&ccb=7-5&ig_cache_key=MzY0MTU1OTU0Njc4OTAxNjM5Nw%3D%3D.3-ccb7-5&oh=00_AfJQ3hG4GdSaYLTY4Hmlr1VMD0NOtlDR72kd9vhd_huFjw&oe=683C520F&_nc_sid=7a9f4b"
    )
    val performance6 = Performance(
        id = 6,
        title = "6월의 낮과밤 먼슬리 라이브!",
        date = "2025.06.15 일요일",
        time = "19:00",
        venue = "클럽낮과밤",
        region = "제주",
        detailLink = "club_day_and_night_jeju",
        isLiked = true,
        isNotified = true,
        posterUrl = "https://scontent-ssn1-1.cdninstagram.com/v/t39.30808-6/447771607_18400328962076740_4130762409041075143_n.jpg?stp=dst-jpg_e35_tt6&efg=eyJ2ZW5jb2RlX3RhZyI6IkNBUk9VU0VMX0lURU0uaW1hZ2VfdXJsZ2VuLjcwMHg3MDAuc2RyLmYzMDgwOC5kZWZhdWx0X2ltYWdlIn0&_nc_ht=scontent-ssn1-1.cdninstagram.com&_nc_cat=104&_nc_oc=Q6cZ2QHxsdExSiDX-YBWWWfLqMPtbJ_Sc7RYCLET8_NZzQ2VyC--c5df-MvIkinitTKnslc&_nc_ohc=tkjZwC_a_DIQ7kNvwGGIE-I&_nc_gid=r3wvISH4olYs6Gq5zl5Lkg&edm=AP4sbd4AAAAA&ccb=7-5&ig_cache_key=MzM4MjMwNTY3MjE4MzUxMTA3Ng%3D%3D.3-ccb7-5&oh=00_AfL8Ahk-A0y_uqJtowRv7P6Y8rE2LA4F4YVVLRmG3OvPSQ&oe=683C5F93&_nc_sid=7a9f4b"
    )

    val all = listOf(performance1, performance2, performance3, performance4, performance5, performance6)
}
