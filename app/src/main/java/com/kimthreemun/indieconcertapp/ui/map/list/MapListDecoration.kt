package com.kimthreemun.indieconcertapp.ui.map.list


import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class EvenSpacingItemDecoration(
    private val context: Context,
    private val spanCount: Int,
    private val spacingDp: Int
) : RecyclerView.ItemDecoration() {


    // dp 값을 px로 변환
    private fun dpToPx(dp: Int): Int {
        val density = context.resources.displayMetrics.density
        return (dp * density).toInt()
    }


    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val spacingPx = dpToPx(spacingDp)
        val column = position % spanCount


        // 2열(column == 1)에만 좌우 여백 적용
//        if (column == 1) {
//            outRect.left = spacingPx
//            outRect.right = spacingPx
//        } else {
//            outRect.left = 0
//            outRect.right = 0
//        }


        outRect.top = 0
        outRect.bottom = 0
    }
}
