package com.kimthreemun.indieconcertapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue

class VenueAdapter(private val items: List<Venue>) :
    RecyclerView.Adapter<VenueAdapter.VenueViewHolder>() {

    inner class VenueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile: ImageView = itemView.findViewById(R.id.img_profile)
        val tvVenueName: TextView = itemView.findViewById(R.id.tvArtistName) // 재사용된 ID 이름
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue, parent, false)
        return VenueViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        val item = items[position]
        holder.tvVenueName.text = item.name
        Glide.with(holder.itemView).load(item.profileImageResId).into(holder.ivProfile)
    }

    override fun getItemCount(): Int = items.size
}
