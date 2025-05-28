package com.kimthreemun.indieconcertapp.ui.map.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.data.model.domain.Performance

class MapListAdapter(
    private var performances: List<Performance>,
    private val onItemClick: (Performance) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 아이템 타입 정의
    companion object {
        private const val TYPE_POSTER_ROW = 0
        private const val TYPE_SELECT_ROW = 1
    }


    // 줄 단위로 묶은 리스트
    private val rows = mutableListOf<RowItem>()
    private var selectedRowPosition: Int? = null


    init {
        updateData(performances)
    }


    // Row 데이터 모델
    sealed class RowItem {
        data class PosterRow(val items: List<Performance>) : RowItem()
        data class SelectRow(val performance: Performance) : RowItem()
    }


    override fun getItemViewType(position: Int): Int {
        return when (rows[position]) {
            is RowItem.PosterRow -> TYPE_POSTER_ROW
            is RowItem.SelectRow -> TYPE_SELECT_ROW
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_POSTER_ROW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_map_poster_set, parent, false) // 아래에서 만들 파일
                PosterRowViewHolder(view)
            }


            TYPE_SELECT_ROW -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_map_select, parent, false)
                SelectRowViewHolder(view)
            }


            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }


    override fun getItemCount(): Int = rows.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = rows[position]) {
            is RowItem.PosterRow -> (holder as PosterRowViewHolder).bind(item.items)
            is RowItem.SelectRow -> (holder as SelectRowViewHolder).bind(item.performance)
        }
    }


    inner class PosterRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(performances: List<Performance>) {
            val posterViews = listOf<View>(
                itemView.findViewById(R.id.map_poster1),
                itemView.findViewById(R.id.map_poster2),
                itemView.findViewById(R.id.map_poster3)
            )


            posterViews.forEachIndexed { index, view ->
                if (index < performances.size) {
                    val performance = performances[index]


                    val imageView = view.findViewById<ImageView>(R.id.iv_poster)
                    val venue = view.findViewById<TextView>(R.id.tv_venue)
                    val time = view.findViewById<TextView>(R.id.tv_time)


                    venue.text = performance.venue
                    time.text = performance.time
                    Glide.with(view.context)
                        .load(performance.posterImageResId ?: R.drawable.sample_poster)
                        .into(imageView)


                    view.setOnClickListener {
                        val rowPos = bindingAdapterPosition
                        if (selectedRowPosition == rowPos + 1) {
                            rows.removeAt(rowPos + 1)
                            selectedRowPosition = null
                            notifyItemRemoved(rowPos + 1)
                        } else {
                            selectedRowPosition?.let {
                                rows.removeAt(it)
                                notifyItemRemoved(it)
                            }
                            rows.add(rowPos + 1, RowItem.SelectRow(performance))
                            selectedRowPosition = rowPos + 1
                            notifyItemInserted(rowPos + 1)
                        }
                    }


                    view.visibility = View.VISIBLE
                } else {
                    view.visibility = View.INVISIBLE
                }
            }
        }
    }



    inner class SelectRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(performance: Performance) {
            // 여기에 공연 정보 바인딩
            itemView.findViewById<TextView>(R.id.tv_title).text = performance.title
            itemView.findViewById<TextView>(R.id.tv_venue).text = performance.venue
            itemView.findViewById<TextView>(R.id.tv_time).text = performance.time
            itemView.findViewById<TextView>(R.id.tv_address).text = performance.address
            Glide.with(itemView.context)
                .load(performance.posterImageResId)
                .placeholder(R.drawable.sample_poster)
                .into(itemView.findViewById(R.id.iv_poster))
        }
    }


    fun updateData(newPerformances: List<Performance>) {
        performances = newPerformances
        rows.clear()
        selectedRowPosition = null
        for (i in performances.indices step 3) {
            val sublist = performances.subList(i, minOf(i + 3, performances.size))
            rows.add(RowItem.PosterRow(sublist))
        }
        notifyDataSetChanged()
    }
}
