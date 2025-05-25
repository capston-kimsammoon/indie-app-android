package com.kimthreemun.indieconcertapp.ui.community.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R

data class Post(
    val title: String,
    val content: String,
    val commentCount: Int,
    val time: String,
    val author: String
)

class BoardAdapter(
    private val context: Context,
    private val postList: List<Post>
) : RecyclerView.Adapter<BoardAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.nickname)
        val content: TextView = itemView.findViewById(R.id.content)
        val commentCount: TextView = itemView.findViewById(R.id.comment_count_1)
        val time: TextView = itemView.findViewById(R.id.post_time_1)
        val author: TextView = itemView.findViewById(R.id.post_author_1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = postList[position]
        holder.title.text = post.title
        holder.content.text = post.content
        holder.commentCount.text = post.commentCount.toString()
        holder.time.text = post.time
        holder.author.text = post.author
    }

    override fun getItemCount(): Int = postList.size
}
