package com.kimthreemun.indieconcertapp.ui.calendar.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class DailyPerformanceAdapter(
    private val performanceList: List<Performance>,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<DailyPerformanceAdapter.DailyPerformanceViewHolder>() {

    inner class DailyPerformanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvVenue: TextView = view.findViewById(R.id.tvVenue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyPerformanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_performance_title_venue, parent, false)
        return DailyPerformanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyPerformanceViewHolder, position: Int) {
        val performance = performanceList[position]

        // dp → px 변환 (12dp → px)
        val radiusPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12f,
            holder.itemView.context.resources.displayMetrics
        ).toInt()

        Glide.with(holder.itemView)
            .load(performance.posterUrl)
            .transform(RoundedCorners(radiusPx))
            .into(holder.imgPoster)

        holder.tvTitle.text = performance.title
        holder.tvVenue.text = performance.venue

        holder.itemView.setOnClickListener {
            onItemClick(performance)
        }
    }

    override fun getItemCount(): Int = performanceList.size
}
