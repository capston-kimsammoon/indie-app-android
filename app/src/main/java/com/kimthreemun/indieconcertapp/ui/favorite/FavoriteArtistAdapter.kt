// FavoriteArtistAdapter.kt
package com.kimthreemun.indieconcertapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.databinding.ItemFavoriteArtistBinding
import com.kimthreemun.indieconcertapp.data.model.domain.Artist
import com.kimthreemun.indieconcertapp.databinding.ItemArtistBinding

class FavoriteArtistAdapter(
    private var items: List<Artist>,
    private val onHeartClick: (Artist) -> Unit, // Int → Artist
    private val onNotifyClick: (Artist) -> Unit
) : RecyclerView.Adapter<FavoriteArtistAdapter.FavoriteArtistViewHolder>() {

    inner class FavoriteArtistViewHolder(private val binding: ItemFavoriteArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Artist) {
            binding.tvArtistName.text = item.name
            Glide.with(binding.root.context)
                .load(item.profileImageResId ?: R.drawable.sample_profile)
                .placeholder(R.drawable.sample_profile)
                .error(R.drawable.sample_profile)
                .circleCrop()
                .into(binding.ivProfile)

            // 알림 버튼
            binding.btnNotify.setImageResource(
                if (item.isNotified) R.drawable.ic_notify_on else R.drawable.ic_notify_off
            )

            // 좋아요 버튼 이미지
            binding.btnLike.setImageResource(
                if (item.isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outline
            )

            // 클릭 리스너
            binding.btnLike.setOnClickListener {
                onHeartClick(item) // Artist 객체 넘김
            }

            binding.btnNotify.setOnClickListener {
                onNotifyClick(item) // Artist 객체 넘김
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteArtistViewHolder {
        val binding = ItemFavoriteArtistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavoriteArtistViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoriteArtistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<Artist>) {
        items = newItems
        notifyDataSetChanged() // 리스트 갱신 시 UI 반영
    }
}
