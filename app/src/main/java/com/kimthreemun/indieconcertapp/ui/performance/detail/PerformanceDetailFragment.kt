package com.kimthreemun.indieconcertapp.ui.performance.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentPerformanceDetailBinding
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class PerformanceDetailFragment : Fragment() {

    private var _binding: FragmentPerformanceDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: PerformanceDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPerformanceDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PerformanceDetailAdapter(mutableListOf())
        binding.rvArtists.adapter = adapter
        binding.rvArtists.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // 🎯 여기서 테스트용 더미 데이터 넣기
        val testArtists = listOf(
            Artist(id = 1, name = "하츄", profileImageResId = R.drawable.sample_profile),
            Artist(id = 2, name = "핑츄", profileImageResId = R.drawable.sample_profile),
            Artist(id = 3, name = "추추", profileImageResId = R.drawable.sample_profile)
        )


        adapter.setData(testArtists)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
