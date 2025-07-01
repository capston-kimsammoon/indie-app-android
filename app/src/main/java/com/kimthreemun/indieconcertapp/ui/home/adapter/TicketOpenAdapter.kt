package com.kimthreemun.indieconcert.ui.home.adapter

import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.ItemTicketOpenBinding
import androidx.core.content.ContextCompat

class TicketOpenAdapter(
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<TicketOpenAdapter.ViewHolder>() {

    private val items = mutableListOf<Performance>()

    fun submitList(data: List<Performance>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemTicketOpenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Performance) {
            binding.tvTitle.text = item.title
            binding.tvVenue.text = item.venue

            binding.root.setOnClickListener {
                onItemClick(item)
            }

            binding.tvTicketDate.apply {
                text = "예매오픈\n${item.ticketOpenDate ?: "-"}"
                setTextColor(ContextCompat.getColor(binding.root.context, R.color.theme_orange))
            }


            // dp → px 변환
            val radiusPx = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                12f,
                binding.root.context.resources.displayMetrics
            ).toInt()

            Glide.with(binding.root)
                .load(item.posterUrl)
                .thumbnail(0.1f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .centerCrop()
                .transform(RoundedCorners(radiusPx))
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
