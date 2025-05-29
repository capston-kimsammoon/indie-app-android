package com.kimthreemun.indieconcertapp.ui.venue.list


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Venue


class VenueListAdapter :
    ListAdapter<Venue, VenueListAdapter.VenueListViewHolder>(VenueDiffCallback()) {


    class VenueListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProfile: ImageView = itemView.findViewById(R.id.img_profile)
        val tvArtistName: TextView = itemView.findViewById(R.id.tvArtistName)


        fun bind(venue: Venue) {
            tvArtistName.text = venue.name
            venue.profileImageResId?.let {
                imgProfile.setImageResource(it)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenueListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue, parent, false)
        return VenueListViewHolder(view)
    }


    override fun onBindViewHolder(holder: VenueListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}


class VenueDiffCallback : DiffUtil.ItemCallback<Venue>() {
    override fun areItemsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(oldItem: Venue, newItem: Venue): Boolean {
        return oldItem == newItem
    }
}
