package com.kimthreemun.indieconcertapp.ui.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.ui.notification.NotificationAdapter
import com.kimthreemun.ui.notification.NotificationData
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController

class NotificationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NotificationAdapter
    private lateinit var ivBack: ImageView
    private lateinit var ivHamburger: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notification, container, false)

        recyclerView = view.findViewById(R.id.rvNotifications)
        ivBack = view.findViewById(R.id.ivBack)
        ivHamburger = view.findViewById(R.id.ivHamburger)

        setupRecyclerView()
        setupClickListeners()

        return view
    }

    private fun setupRecyclerView() {
        val dummyList = mutableListOf(
            NotificationData("김상문 님의 새로운 공연이 등록되었습니다.", "김상문"),
            NotificationData("김상문단독콘서트 예매 오픈 5분 전입니다.", "김상문단독콘서트"),
            NotificationData("내 게시물에 답글이 달렸습니다.", "답글")
        )

        adapter = NotificationAdapter(dummyList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
    }

    private fun setupClickListeners() {
        ivBack.setOnClickListener {
            findNavController().popBackStack()
        }

        // ✅ Drawer 열기
        ivHamburger.setOnClickListener {
            val drawer = requireActivity().findViewById<DrawerLayout>(R.id.drawer_layout)
            drawer.openDrawer(GravityCompat.START)
        }
    }
}
