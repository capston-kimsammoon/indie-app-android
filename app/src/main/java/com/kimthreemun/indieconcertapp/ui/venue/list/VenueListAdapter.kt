package com.kimthreemun.indieconcertapp.ui.venue.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue

class VenueListAdapter(private val venueList: List<Venue>) :
    RecyclerView.Adapter<VenueListAdapter.VenueListViewHolder>() {

    class VenueListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.img_profile)
        val tvArtistName: TextView = itemView.findViewById(R.id.tvArtistName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue, parent, false)
        return VenueListViewHolder(view)
    }

    override fun onBindViewHolder(holder: VenueListViewHolder, position: Int) {
        val venue = venueList[position]
        holder.tvArtistName.text = venue.name

        venue.profileImageResId?.let {
            holder.imgProfile.setImageResource(it)
        }
    }

    override fun getItemCount(): Int = venueList.size
}
