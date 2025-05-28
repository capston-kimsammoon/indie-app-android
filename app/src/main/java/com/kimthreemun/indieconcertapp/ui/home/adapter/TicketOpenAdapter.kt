package com.kimthreemun.indieconcert.ui.home.adapter

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcert.data.model.domain.Concert
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.ItemTicketOpenBinding

class TicketOpenAdapter : RecyclerView.Adapter<TicketOpenAdapter.ViewHolder>() {

    private val items = mutableListOf<Concert>()

    fun submitList(data: List<Concert>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTicketOpenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Concert) {
            binding.tvTitle.text = item.title
            binding.tvPlace.text = item.place

            binding.tvTicketDate.apply {
                text = "예매오픈\n${item.ticketOpenDate ?: "-"}"
                setTextColor(Color.parseColor("#F14F21"))
            }

            // dp → px 변환
            val radiusPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                12f,
                binding.root.context.resources.displayMetrics
            ).toInt()

            Glide.with(binding.root)
                .load(item.posterUrl)
                .placeholder(R.drawable.bg_poster_rounded)
                .error(R.drawable.bg_poster_rounded)
                .thumbnail(0.1f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .transform(RoundedCorners(radiusPx)) // ✅ 둥근 모서리 적용
                .into(binding.ivPoster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTicketOpenBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size
}
