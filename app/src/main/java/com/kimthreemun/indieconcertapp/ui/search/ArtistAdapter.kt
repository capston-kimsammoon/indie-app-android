package com.kimthreemun.indieconcertapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
class ArtistAdapter(private val items: List<Artist>) :
    RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivProfile: ImageView = itemView.findViewById(R.id.ivProfile)
        val tvArtistName: TextView = itemView.findViewById(R.id.tvArtistName)
        val btnHeart: ImageButton = itemView.findViewById(R.id.btnHeart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ArtistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val item = items[position]
        holder.tvArtistName.text = item.name
        // Glide.with(holder.itemView).load(item.profileUrl).into(holder.ivProfile)
        // 좋아요 버튼 처리도 여기에 추가 가능
    }

    override fun getItemCount(): Int = items.size
}


