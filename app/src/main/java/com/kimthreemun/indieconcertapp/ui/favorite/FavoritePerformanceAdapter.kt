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

            Glide.with(binding.root.context)
                .load(performance.posterUrl) // 이건 URL 또는 resId 로직에 따라 조절
                .placeholder(R.drawable.sample_poster)
                .error(R.drawable.sample_poster)
                .into(binding.imgPoster)

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
