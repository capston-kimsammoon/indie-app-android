package com.kimthreemun.indieconcert.ui.home.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.databinding.ItemTodayShowBinding
import com.kimthreemun.indieconcertapp.R

class WeeklyPerformanceAdapter(
    private val onGoClick: () -> Unit,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<WeeklyPerformanceAdapter.ViewHolder>() {

    private val items = mutableListOf<Performance>()

    fun submitList(data: List<Performance>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTodayShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Performance) {
            binding.tvTitle.text = item.title
            binding.tvVenue.text = item.venue
            binding.tvDate.text = item.date

            binding.ivGo.setOnClickListener {
                onGoClick()
            }

            binding.root.setOnClickListener {
                onItemClick(item)
            }

            // radius dp → px 변환
            val radiusInPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8f,
                binding.root.context.resources.displayMetrics
            ).toInt()

            Glide.with(binding.root)
                .load(item.posterUrl)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .dontAnimate()
                .centerCrop()
                .transform(RoundedCorners(radiusInPx))
                .into(binding.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTodayShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}
