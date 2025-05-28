package com.kimthreemun.indieconcertapp.ui.artist.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.databinding.ItemArtistBinding
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Artist

class ArtistListAdapter(
    private var artists: MutableList<Artist>,
    private val onHeartClick: (Int) -> Unit,
    private val onItemClick: (Artist) -> Unit
) : RecyclerView.Adapter<ArtistListAdapter.ArtistViewHolder>() {

    inner class ArtistViewHolder(private val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            binding.tvArtistName.text = item.name

            Glide.with(binding.root.context)
                .load(item.profileImageUrl.ifEmpty { null } ?: item.profileImageResId)
                .placeholder(R.drawable.sample_profile)
                .error(R.drawable.sample_profile)
                .circleCrop()
                .into(binding.ivProfile)

            binding.btnHeart.setImageResource(
                if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )

            // 좋아요 버튼 클릭 시 artist.id(Int) 넘기기
            binding.btnHeart.setOnClickListener {
                onHeartClick(item.id)
            }

            // 전체 아이템 클릭 시 artist.id(String) 넘기기 - id 타입 맞춰서 변경 필요
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistViewHolder {
        val binding = ItemArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        val artist = artists[position]
        holder.bind(artist)
    }

    override fun getItemCount(): Int = artists.size

    fun updateList(newArtists: List<Artist>) {
        artists.clear()
        artists.addAll(newArtists)
        notifyDataSetChanged()
    }
}