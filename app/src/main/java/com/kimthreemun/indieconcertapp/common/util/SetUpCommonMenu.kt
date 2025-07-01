// /common/util/SetUpCommonMenu.kt
package com.kimthreemun.indieconcertapp.common.util

import android.view.Menu
import android.widget.TextView
import com.kimthreemun.indieconcertapp.R

fun setupNavigationMenu(menu: Menu) {
    for (i in 0 until menu.size()) {
        val item = menu.getItem(i)
        val actionView = item.actionView
        val titleView = actionView?.findViewById<TextView>(R.id.title)

        // 메뉴 위치 기준으로 텍스트 직접 지정
        when (item.itemId) {
            R.id.nav_performance -> titleView?.text = "공연"
            R.id.nav_venue -> titleView?.text = "공연장"
            R.id.nav_artist -> titleView?.text = "아티스트"
            R.id.nav_board -> titleView?.text = "자유게시판"
            R.id.nav_nearby -> titleView?.text = "가까운 공연 찾기"
        }
    }
}


