package com.kimthreemun.indieconcertapp.ui.artist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.databinding.ItemArtistBinding
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class ArtistListAdapter(
    private var items: List<Artist>,
    private val onHeartClick: (Int) -> Unit
) : RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(private val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            binding.tvArtistName.text = item.name
            binding.imgProfile.setImageResource(
                item.profileImageResId ?: R.drawable.sample_profile
            )
            binding.btnHeart.setImageResource(
                if (item.isLiked) R.drawable.ic_heart_filled
                else R.drawable.ic_heart_outline
            )

            binding.btnHeart.setOnClickListener {
                onHeartClick(item.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(newItems: List<Artist>) {
        items = newItems
        notifyDataSetChanged()
    }
}
