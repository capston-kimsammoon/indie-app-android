package com.kimthreemun.indieconcertapp.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Post

class BoardAdapter(private val items: List<Post>) :
    RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    inner class BoardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitle: TextView = itemView.findViewById(R.id.postTitle)
        val postContent: TextView = itemView.findViewById(R.id.postContent)
        val postImage: ImageView = itemView.findViewById(R.id.postImage)
        val postInfo: TextView = itemView.findViewById(R.id.postInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post, parent, false)
        return BoardViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        val item = items[position]
        holder.postTitle.text = item.title
        holder.postContent.text = item.content
        holder.postInfo.text = "${item.nickname} · ${item.commentCount}개 댓글 · ${item.createdAt}"

        if (item.imageUrl.isNullOrEmpty()) {
            holder.postImage.visibility = View.GONE
        } else {
            holder.postImage.visibility = View.VISIBLE
            // Glide.with(holder.itemView).load(item.imageUrl).into(holder.postImage)
        }
    }

    override fun getItemCount(): Int = items.size
}
