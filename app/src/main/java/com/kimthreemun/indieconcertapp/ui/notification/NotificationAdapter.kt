package com.kimthreemun.ui.notification

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R

class NotificationAdapter(
    private val items: MutableList<NotificationData>
) : RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvContent: TextView = itemView.findViewById(R.id.tvNotificationTitle)
        val ivClose: ImageView = itemView.findViewById(R.id.ivClose)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        val item = items[position]
        val content = item.content
        val highlight = item.highlight

        val spannable = SpannableString(content)
        val start = content.indexOf(highlight)
        val end = start + highlight.length

        if (start >= 0) {
            spannable.setSpan(
                ForegroundColorSpan(Color.parseColor("#007AFF")), // 파란색
                start,
                end,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }

        holder.tvContent.text = spannable

        holder.ivClose.setOnClickListener {
            items.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, items.size)
        }
    }

    override fun getItemCount(): Int = items.size
}
