package com.kimthreemun.indieconcertapp.ui.community.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    private val comments = mutableListOf<Comment>()

    fun submitList(newList: List<Comment>) {
        comments.clear()
        comments.addAll(newList)
        notifyDataSetChanged()
    }

    inner class CommentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comment: Comment) {
            val content = if (comment.parentCommentId != null) {
                "↳ ${comment.content}"
            } else {
                comment.content
            }

            itemView.findViewById<TextView>(R.id.commentNickname).text = comment.nickname
            itemView.findViewById<TextView>(R.id.commentContent).text = content
            itemView.findViewById<TextView>(R.id.commentTime).text = comment.createdAt
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comments[position])
    }
}
