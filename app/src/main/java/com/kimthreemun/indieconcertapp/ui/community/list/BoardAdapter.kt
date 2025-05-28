package com.kimthreemun.indieconcertapp.ui.community.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.data.model.dto.post.PostDto
import com.kimthreemun.indieconcertapp.databinding.ItemBoardPostBinding
class BoardAdapter(
    private var items: List<PostDto>,
    private val onItemClick: (PostDto) -> Unit
) : RecyclerView.Adapter<BoardAdapter.BoardViewHolder>() {

    inner class BoardViewHolder(val binding: ItemBoardPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostDto) {
            binding.tvTitle.text = post.title
            binding.tvContent.text = post.content
            binding.tvDate.text = post.date
            binding.tvAuthor.text = post.author
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBoardPostBinding.inflate(inflater, parent, false)
        return BoardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<PostDto>) {
        items = newItems
        notifyDataSetChanged()
    }
}


