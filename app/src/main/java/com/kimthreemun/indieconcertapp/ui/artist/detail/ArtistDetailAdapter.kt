package com.kimthreemun.indieconcertapp.ui.artist.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import java.text.SimpleDateFormat
import java.util.*

class ArtistDetailAdapter(
    private var performances: MutableList<Performance>,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<ArtistDetailAdapter.PerformanceViewHolder>() {

    inner class PerformanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster: ImageView = itemView.findViewById(R.id.ivPoster)
        val tvPerformanceTitle: TextView = itemView.findViewById(R.id.tvPerformanceTitle)
        val tvPerformanceDate: TextView = itemView.findViewById(R.id.tvPerformanceDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_performance_thumbnail, parent, false)
        return PerformanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PerformanceViewHolder, position: Int) {
        val performance = performances[position]
        holder.tvPerformanceTitle.text = performance.title
        holder.tvPerformanceDate.text = performance.date
        Glide.with(holder.itemView.context)
            .load(performance.posterUrl)
            .placeholder(R.drawable.sample_poster)
            .error(R.drawable.sample_poster)
            .into(holder.ivPoster)

        holder.itemView.setOnClickListener {
            onItemClick(performance)
        }
    }

    override fun getItemCount(): Int = performances.size

    fun setData(newPerformances: List<Performance>) {
        performances.clear()
        performances.addAll(newPerformances)
        notifyDataSetChanged()
    }
}
