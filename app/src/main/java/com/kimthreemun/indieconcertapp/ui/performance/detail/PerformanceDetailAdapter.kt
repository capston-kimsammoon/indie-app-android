package com.kimthreemun.indieconcertapp.ui.performance.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class PerformanceDetailAdapter(
    private val artists: MutableList<Artist>
) : RecyclerView.Adapter<PerformanceDetailAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        val tvName: TextView = itemView.findViewById(R.id.tvArtistName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.tvName.text = artist.name

        val imageSource = artist.profileImageResId
            ?: artist.profileImageUrl.takeIf { it.isNotBlank() }

        Glide.with(holder.itemView.context)
            .load(imageSource)
            .circleCrop()
            .into(holder.ivProfile)
    }

    override fun getItemCount(): Int = artists.size

    fun setData(newArtists: List<Artist>) {
        artists.clear()
        artists.addAll(newArtists)
        notifyDataSetChanged()
    }
}
