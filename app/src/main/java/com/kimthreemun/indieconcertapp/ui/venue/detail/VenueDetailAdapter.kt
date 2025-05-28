package com.kimthreemun.indieconcertapp.ui.venue.detail


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance


class VenueDetailAdapter(
    private val performances: MutableList<Performance>
) : RecyclerView.Adapter<VenueDetailAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivPoster: ImageView = view.findViewById(R.id.ivPoster)
        val tvPerformanceTitle: TextView = view.findViewById(R.id.tvPerformanceTitle)
        val tvPerformanceDate: TextView = view.findViewById(R.id.tvPerformanceDate)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_performance_thumbnail, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val performance = performances[position]
        holder.tvPerformanceTitle.text = performance.title
        holder.tvPerformanceDate.text = performance.date


        performance.posterImageResId?.let { resId ->
            Glide.with(holder.itemView.context)
                .load(resId)
                .placeholder(R.drawable.venue_sample2)
                .error(R.drawable.venue_sample2)
                .into(holder.ivPoster)
        } ?: run {
            Glide.with(holder.itemView.context)
                .load(R.drawable.venue_sample2)
                .into(holder.ivPoster)
        }
    }


    override fun getItemCount(): Int = performances.size


    fun setData(newPerformances: List<Performance>) {
        performances.clear()
        performances.addAll(newPerformances)
        notifyDataSetChanged()
    }
}
