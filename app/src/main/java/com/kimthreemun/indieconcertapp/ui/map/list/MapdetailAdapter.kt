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


class MapDetailAdapter(
    private val performances: MutableList<Performance>
) : RecyclerView.Adapter<MapDetailAdapter.PerformanceViewHolder>() {


    inner class PerformanceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivPoster: ImageView = itemView.findViewById(R.id.iv_poster)
        val tvName: TextView = itemView.findViewById(R.id.tv_venue)
        val tvTime: TextView = itemView.findViewById(R.id.tv_time)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerformanceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_postery, parent, false)
        return PerformanceViewHolder(view)
    }


    override fun onBindViewHolder(holder: PerformanceViewHolder, position: Int) {
        val performance = performances[position]
        holder.tvName.text = performance.title
        Glide.with(holder.itemView.context)
            .load(performance.posterUrl)
            //.circleCrop()
            .into(holder.ivPoster)
    }


    override fun getItemCount(): Int = performances.size


    fun setData(newArtists: List<Performance>) {
        performances.clear()
        performances.addAll(newArtists)
        notifyDataSetChanged()
    }
}
