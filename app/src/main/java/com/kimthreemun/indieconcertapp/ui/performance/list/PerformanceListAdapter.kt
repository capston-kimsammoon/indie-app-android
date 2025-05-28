// /ui/performance/list/PerformanceListAdapter.kt
package com.kimthreemun.indieconcertapp.ui.performance.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class PerformanceListAdapter(
    private var performances: List<Performance>,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<PerformanceListAdapter.PerformanceViewHolder>() {

    inner class PerformanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imgPoster)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val venue: TextView = itemView.findViewById(R.id.tvVenue)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_performance, parent, false)
        return PerformanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerformanceViewHolder, position: Int) {
        val performance = performances[position]
        holder.title.text = performance.title
        holder.venue.text = performance.venue
        holder.date.text = performance.date

        Glide.with(holder.poster.context)
            .load(performance.posterUrl)
            .placeholder(R.drawable.sample_poster)  // 이미지 로딩 전 기본 이미지 (sample_poster.png를 drawable에 넣으세요)
            .into(holder.poster)

        holder.itemView.setOnClickListener {
            onItemClick(performance)
        }
    }

    override fun getItemCount(): Int = performances.size

    fun updateData(newPerformances: List<Performance>) {
        performances = newPerformances
        notifyDataSetChanged()
    }
}
