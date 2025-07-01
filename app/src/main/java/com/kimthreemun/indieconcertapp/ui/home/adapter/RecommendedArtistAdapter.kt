package com.kimthreemun.indieconcert.ui.home.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.ItemPerformanceTitleVenueDateBinding

class RecommendedArtistAdapter(
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<RecommendedArtistAdapter.ViewHolder>() {

    private val items = mutableListOf<Performance>()

    fun submitList(data: List<Performance>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemPerformanceTitleVenueDateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Performance) {
            binding.tvTitle.text = item.title
            binding.tvVenue.text = item.venue
            binding.tvDate.text = item.date

            binding.root.setOnClickListener {
                onItemClick(item)
            }

            // dp → px 변환
            val radiusPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8f,
                binding.root.context.resources.displayMetrics
            ).toInt()

            Glide.with(binding.root)
                .load(item.posterUrl)
                .transform(RoundedCorners(radiusPx))
                .into(binding.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPerformanceTitleVenueDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
