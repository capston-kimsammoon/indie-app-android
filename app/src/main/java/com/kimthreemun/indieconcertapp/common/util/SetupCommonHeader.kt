package com.kimthreemun.indieconcertapp.common.util

import android.view.View
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.kimthreemun.indieconcertapp.R

fun SetupCommonHeader(
    view: View,
    title: String,
    showMenu: Boolean = true,
    showBack: Boolean = true,
    showSearch: Boolean = true
) {
    val iconBack = view.findViewById<View>(R.id.iconBack)
    val iconMenu = view.findViewById<View>(R.id.iconMenu)
    val iconSearch = view.findViewById<View>(R.id.iconSearch)
    val headerTitle = view.findViewById<TextView>(R.id.headerTitle)

    val navController = view.findNavController()

    headerTitle.text = title

    iconBack.visibility = View.VISIBLE
    iconMenu.visibility = View.VISIBLE
    iconSearch.visibility = View.VISIBLE

    iconMenu.visibility = if (showMenu) View.VISIBLE else View.GONE
    iconBack.visibility = if (showBack) View.VISIBLE else View.GONE
    iconSearch.visibility = if (showSearch) View.VISIBLE else View.GONE

    iconBack.setOnClickListener {
        navController.popBackStack()
    }

    iconMenu.setOnClickListener {
        val drawer = view.rootView.findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.openDrawer(GravityCompat.START)
    }

    iconSearch.setOnClickListener {
        navController.navigate(R.id.searchFragment)
    }
}
