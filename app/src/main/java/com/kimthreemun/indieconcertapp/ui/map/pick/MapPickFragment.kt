package com.kimthreemun.indieconcertapp.ui.map.pick

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentMapPickBinding

class MapPickFragment : Fragment() {

    private var _binding: FragmentMapPickBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapPickBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("MapPickFragment", "onViewCreated 실행됨")
        binding.tvTimeInfo.text = getCurrentTimeText()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
