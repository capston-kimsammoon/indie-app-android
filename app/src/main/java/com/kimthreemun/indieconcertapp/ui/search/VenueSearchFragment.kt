package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R

//class VenueSearchFragment : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? = inflater.inflate(R.layout.fragment_venue_search, container, false)
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_venue_search)
//        recyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//        // 추후 VenueViewModel 추가되면 연결
//        recyclerView.adapter = ShowAdapter(emptyList())
//    }
//}