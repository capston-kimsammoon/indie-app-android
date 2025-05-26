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
import com.kimthreemun.indieconcert.data.model.domain.Concert

class DailyConcertAdapter(
    private val concertList: List<Concert>
) : RecyclerView.Adapter<DailyConcertAdapter.DailyConcertViewHolder>() {

    inner class DailyConcertViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPoster: ImageView = view.findViewById(R.id.imgPoster)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvVenue: TextView = view.findViewById(R.id.tvVenue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyConcertViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_daily_concert, parent, false)
        return DailyConcertViewHolder(view)
    }

    override fun onBindViewHolder(holder: DailyConcertViewHolder, position: Int) {
        val concert = concertList[position]

        // dp → px 변환 (12dp → px)
        val radiusPx = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            12f,
            holder.itemView.context.resources.displayMetrics
        ).toInt()

        Glide.with(holder.itemView)
            .load(concert.posterUrl)
            .placeholder(R.drawable.bg_poster_rounded)
            .transform(RoundedCorners(radiusPx)) // ✅ 모서리 둥글게 처리
            .into(holder.imgPoster)

        holder.tvTitle.text = concert.title
        holder.tvVenue.text = concert.place
    }

    override fun getItemCount(): Int = concertList.size
}
