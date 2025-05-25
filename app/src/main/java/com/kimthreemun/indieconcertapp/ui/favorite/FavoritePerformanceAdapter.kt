package com.kimthreemun.indieconcertapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.ItemFavoritePerformanceBinding

class FavoritePerformanceAdapter(
    private var performances: List<Performance>,
    private val onItemClick: (Performance) -> Unit,
    private val onHeartClick: (Performance) -> Unit
) : RecyclerView.Adapter<FavoritePerformanceAdapter.FavoritePerformanceViewHolder>() {

    inner class FavoritePerformanceViewHolder(
        private val binding: ItemFavoritePerformanceBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(performance: Performance) {
            binding.tvTitle.text = performance.title
            binding.tvVenue.text = performance.venue
            binding.tvDate.text = performance.date

            // 포스터 이미지 로딩
            Glide.with(binding.root.context)
                .load(performance.posterImageResId) // URL 또는 리소스 ID
                .placeholder(R.drawable.sample_poster)
                .error(R.drawable.sample_poster)
                .into(binding.imgPoster)

            // 클릭 이벤트
            binding.root.setOnClickListener {
                onItemClick(performance)
            }

            binding.btnLike.setImageResource(
                if (performance.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )

            binding.btnLike.setOnClickListener {
                onHeartClick(performance)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePerformanceViewHolder {
        val binding = ItemFavoritePerformanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoritePerformanceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritePerformanceViewHolder, position: Int) {
        holder.bind(performances[position])
    }

    override fun getItemCount(): Int = performances.size

    fun updateData(newPerformances: List<Performance>) {
        performances = newPerformances
        notifyDataSetChanged()
    }
}
