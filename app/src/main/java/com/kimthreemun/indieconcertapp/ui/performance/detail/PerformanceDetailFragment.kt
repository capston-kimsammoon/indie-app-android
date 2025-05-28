package com.kimthreemun.indieconcertapp.ui.performance.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.navigation.fragment.findNavController
import com.kimthreemun.indieconcertapp.ui.performance.detail.PerformanceDetailFragmentDirections
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.FragmentPerformanceDetailBinding
import com.kimthreemun.indieconcertapp.common.util.SetupCommonHeader

class PerformanceDetailFragment : Fragment() {

    private var _binding: FragmentPerformanceDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PerformanceDetailViewModel by viewModels()
    private lateinit var adapter: PerformanceDetailAdapter

    private val args: PerformanceDetailFragmentArgs by navArgs()

    private val radiusPx by lazy {
        (16 * resources.displayMetrics.density).toInt()  // 16dp를 px로 변환
    }

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

        // navArgs에서 넘겨받은 Performance 객체 받기
        val performance = args.performance

        // ViewModel에 전달
        viewModel.setPerformance(performance)

        SetupCommonHeader(view, title = "공연")

        adapter = PerformanceDetailAdapter(mutableListOf()) { artist ->
            val action = PerformanceDetailFragmentDirections
                .actionPerformanceDetailFragmentToArtistDetailFragment(artist)
            findNavController().navigate(action)
        }

        binding.rvArtists.adapter = adapter
        binding.rvArtists.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        // ViewModel 관찰
        viewModel.performanceDetail.observe(viewLifecycleOwner) { performanceDetail ->
            // UI에 데이터 바인딩
            adapter.setData(performanceDetail.artists)

            binding.tvTitle.text = performanceDetail.title
            binding.tvVenue.text = performanceDetail.venue
            binding.tvDate.text = performanceDetail.date
            binding.tvLikeCnt.text = performanceDetail.likeCount.toString()

            binding.ivHeart.setImageResource(
                if (performanceDetail.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )

            binding.btnNotify.setImageResource(
                if (performanceDetail.isNotified) R.drawable.ic_notify_on else R.drawable.ic_notify_off
            )

            // Glide 로 포스터 이미지도 넣기
            Glide.with(binding.root)
                .load(performanceDetail.posterUrl)
                .transform(RoundedCorners(radiusPx))
                .into(binding.ivPoster)


        }

        binding.ivHeart.setOnClickListener {
            viewModel.toggleLike()
        }

        binding.btnNotify.setOnClickListener {
            viewModel.toggleNotify()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
