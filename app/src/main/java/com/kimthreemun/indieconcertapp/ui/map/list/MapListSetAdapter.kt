package com.kimthreemun.indieconcertapp.ui.map.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance


class MapListSetAdapter(
    private val posters: List<Performance>,
    private val onPosterClick: (Performance) -> Unit
) : RecyclerView.Adapter<MapListSetAdapter.PosterViewHolder>() {


    inner class PosterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.iv_poster)
        val venue: TextView = itemView.findViewById(R.id.tv_venue)
        val time: TextView = itemView.findViewById(R.id.tv_time)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PosterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_map_poster, parent, false)
        return PosterViewHolder(view)
    }


    override fun onBindViewHolder(holder: PosterViewHolder, position: Int) {
        val performance = posters[position]


        holder.venue.text = performance.venue
        holder.time.text = performance.time


        Glide.with(holder.poster.context)
            .load(performance.posterImageResId ?: R.drawable.sample_poster)
            .into(holder.poster)


        holder.itemView.setOnClickListener {
            onPosterClick(performance)
        }
    }


    override fun getItemCount(): Int = posters.size
}
