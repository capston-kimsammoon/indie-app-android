package com.kimthreemun.indieconcertapp.ui.performance.detail
import android.util.Log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentPerformanceDetailBinding
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class PerformanceDetailFragment : Fragment() {

    private var _binding: FragmentPerformanceDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PerformanceDetailViewModel by viewModels()
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

        viewModel.loadPerformanceDetail(performanceId = 1)

        viewModel.performanceDetail.observe(viewLifecycleOwner) { performance ->
            adapter.setData(performance.artists)
            // 기타 텍스트 바인딩 추가 가능
        }

        viewModel.isLiked.observe(viewLifecycleOwner) { liked ->
            binding.ivHeart.setImageResource(
                if (liked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )
        }

        viewModel.likeCount.observe(viewLifecycleOwner) { count ->
            binding.tvLikeCnt.text = count.toString()
        }

        viewModel.isNotified.observe(viewLifecycleOwner) { notified ->
            binding.btnNotify.apply {
                setImageResource(
                    if (notified) R.drawable.ic_notify_on else R.drawable.ic_notify_off
                )
            }
        }

        binding.ivHeart.setOnClickListener {
            viewModel.toggleLike()
        }

        binding.btnNotify.setOnClickListener {
            viewModel.toggleNotify()
        }

        binding.tvVenue.setOnClickListener {
//            Toast.makeText(requireContext(), "공연장 상세 이동 예정", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
