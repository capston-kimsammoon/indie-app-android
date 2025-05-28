package com.kimthreemun.indieconcertapp.ui.community.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Post

class PostAdapter(
    private val onItemClick: (Post) -> Unit
) : ListAdapter<Post, PostAdapter.PostViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.findViewById<TextView>(R.id.postTitle).text = post.title
            itemView.findViewById<TextView>(R.id.postContent).text = post.content

            val commentCount = DummyCommentData.getCommentCountForPost(post.id)
            val infoText = "${post.nickname} · 댓글 $commentCount · ${post.createdAt}"
            itemView.findViewById<TextView>(R.id.postInfo).text = infoText

            val imageView = itemView.findViewById<ImageView>(R.id.postImage)
            if (!post.imageUrl.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
                Glide.with(itemView)
                    .load(post.imageUrl)
                    .centerCrop() // 이거 꼭 필요함!
                    .into(imageView)
            } else {
                imageView.visibility = View.GONE
            }

            itemView.setOnClickListener {
                onItemClick(post)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
