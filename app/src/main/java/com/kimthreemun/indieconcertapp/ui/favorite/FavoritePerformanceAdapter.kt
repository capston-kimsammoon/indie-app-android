// FavoritePerformanceAdapter.kt
package com.kimthreemun.indieconcertapp.ui.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.ItemFavoritePerformanceBinding
import com.kimthreemun.indieconcertapp.data.model.domain.Performance
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListAdapter
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListAdapter.PerformanceViewHolder

class FavoritePerformanceAdapter(
    private var performances: List<Performance>,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<FavoritePerformanceAdapter.FavoritePerformanceViewHolder>() {

    inner class FavoritePerformanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.imgPoster)
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val venue: TextView = itemView.findViewById(R.id.tvVenue)
        val date: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePerformanceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorite_performance, parent, false)
        return FavoritePerformanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritePerformanceViewHolder, position: Int) {
        val performance = performances[position]
        holder.title.text = performance.title
        holder.venue.text = performance.venue
        holder.date.text = performance.date

        holder.itemView.setOnClickListener {
            onItemClick(performance)
        }
    }

    override fun getItemCount(): Int = performances.size

    fun updateData(newPerformances: List<Performance>) {
        performances = newPerformances
    }

    class DiffCallback : DiffUtil.ItemCallback<Performance>() {
        override fun areItemsTheSame(oldItem: Performance, newItem: Performance) = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: Performance, newItem: Performance) = oldItem == newItem
    }
}
