package com.kimthreemun.indieconcertapp.ui.map.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.FragmentMapBinding


class MapListFragment : Fragment() {

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MapListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MapListAdapter(getSampleData()) { performance ->
            Log.d("MapList", "Clicked: ${performance.title}")
        }

        binding.rvPosters.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.rvPosters.adapter = adapter

        binding.tvTimeInfo.text = getCurrentTimeText()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getSampleData(): List<Performance> {
        return listOf(
            Performance(id = 1, title = "일끝나고콘서트", venue = "CLUB KNOCK", time = "오후 4시",
                address = "인천 미추홀구 숙골로95번길 25 청사프라자 4층 노크", posterImageResId = R.drawable.poster_sample1),
            Performance(id = 2, title = "Beyond Universe", venue = "언드", time = "오후 4시",
                address = "경남 거제시 거제대로 3734 지하1층 언드", posterImageResId = R.drawable.poster_sample2),
            Performance(id = 3, title = "SOUND ATTACK", venue = "HONGDAE 'BENDER'", time = "오후 4시 30분",
                address = "서울 마포구 와우산로14길 4 지하1층", posterImageResId = R.drawable.poster_sample3),
            Performance(id = 4, title = "DUSKY BLUE", venue = "CLUB VICTIM", time = "오후 5시",
                address = "서울 마포구 와우산로 53 지하", posterImageResId = R.drawable.poster_sample4),
            Performance(id = 5, title = "FIRST UNPLUGGED", venue = "스팀펑크락", time = "오후 5시 30분",
                address = "서울 서대문구 연세로9길 13 지하1층", posterImageResId = R.drawable.poster_sample5),
            Performance(id = 6, title = "운해몽", venue = "인터플레이", time = "오후 6시",
                address = "대전 서구 대덕대로162번길 11 지하 인터플레이", posterImageResId = R.drawable.poster_sample6),
            Performance(id = 7, title = "Did you Love me?", venue = "Space Brick", time = "오후 6시 40분",
                address = "서울 마포구 잔다리로 31 지하2층", posterImageResId = R.drawable.poster_sample7),
            Performance(id = 8, title = "OPEN STAGE", venue = "Cafe PPnF", time = "오후 7시",
                address = "서울 종로구 성균관로 87 1F", posterImageResId = R.drawable.poster_sample8),
            Performance(id = 9, title = "Our Universe: 지구부터 우주까지 저기저기저끝까지메롱메롱메롱메롱", venue = "벨로주 홍대", time = "오후 7시50분",
                address = "서울 마포구 잔다리로 46 스튜디오빌딩 지하", posterImageResId = R.drawable.poster_sample9),
            Performance(id = 10, title = "A Place Called Sound", venue = "Commentary Sound", time = "오후 8시",
                address = "서울 마포구 월드컵로23길 18 201호", posterImageResId = R.drawable.poster_sample10),
        )
    }

    private fun getCurrentTimeText(): String {
        val timeZone = java.util.TimeZone.getTimeZone("Asia/Seoul")
        val calendar = java.util.Calendar.getInstance(timeZone)

        val hour = calendar.get(java.util.Calendar.HOUR_OF_DAY)
        val minute = calendar.get(java.util.Calendar.MINUTE)

        val period = if (hour < 12) "오전" else "오후"
        val formattedHour = if (hour % 12 == 0) 12 else hour % 12
        val formattedMinute = if (minute < 10) "0$minute" else "$minute"

        return "$period ${formattedHour}시 ${formattedMinute}분 이후 공연"
    }
}
