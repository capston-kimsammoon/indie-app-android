package com.kimthreemun.indieconcertapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListFragment
import com.kimthreemun.indieconcertapp.ui.performance.detail.PerformanceDetailFragment
import com.kimthreemun.indieconcertapp.ui.artist.list.ArtistListFragment
import com.kimthreemun.indieconcertapp.ui.artist.detail.ArtistDetailFragment
import com.kimthreemun.indieconcertapp.ui.favorite.FavoriteFragment
import com.kimthreemun.indieconcertapp.ui.map.list.MapListFragment
import com.kimthreemun.indieconcertapp.ui.map.pick.MapPickFragment
import com.kimthreemun.indieconcertapp.ui.venue.detail.VenueDetailFragment
import com.kimthreemun.indieconcertapp.ui.venue.list.VenueListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.navigation_view)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.performanceListFragment,
                R.id.venueListFragment,
                R.id.artistListFragment,
                R.id.postListFragment,
                R.id.mapFragment
            ),
            drawerLayout
        )

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_performance -> navController.navigate(R.id.performanceListFragment)
                R.id.nav_venue -> navController.navigate(R.id.venueListFragment)
                R.id.nav_artist -> navController.navigate(R.id.artistListFragment)
                R.id.nav_board -> navController.navigate(R.id.postListFragment)
                R.id.nav_nearby -> navController.navigate(R.id.mapFragment)
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

        val headerView = navView.getHeaderView(0)

        headerView.findViewById<View>(R.id.tvLike)?.setOnClickListener {
            navController.navigate(R.id.bookmarkFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        headerView.findViewById<View>(R.id.layoutNicknameArea)?.setOnClickListener {
            navController.navigate(R.id.mypageFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        headerView.findViewById<View>(R.id.ivNotification)?.setOnClickListener {
            navController.navigate(R.id.notificationFragment)
            drawerLayout.closeDrawer(GravityCompat.START)
        }

        headerView.findViewById<View>(R.id.ivBack)?.setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    fun openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment).navController
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
