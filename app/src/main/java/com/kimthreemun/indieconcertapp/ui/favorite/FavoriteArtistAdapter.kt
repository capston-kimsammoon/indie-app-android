// FavoriteArtistAdapter.kt
package com.kimthreemun.indieconcertapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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
            binding.imgProfile.setImageResource(
                item.profileImageResId ?: R.drawable.sample_profile
            )


            // 알림 버튼 텍스트 및 아이콘
            binding.btnNotify.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                if (item.isNotified) R.drawable.ic_notify_on else R.drawable.ic_notify_off, 0)

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

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FavoriteArtistViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(newItems: List<Artist>) {
        items = newItems
        notifyDataSetChanged() // 리스트 갱신 시 UI 반영
    }
}
