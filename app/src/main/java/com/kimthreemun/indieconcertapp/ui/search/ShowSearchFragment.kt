package com.kimthreemun.indieconcertapp.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kimthreemun.indieconcertapp.R
import com.kimthreemun.indieconcertapp.ui.performance.list.PerformanceListViewModel

class ShowSearchFragment : Fragment(R.layout.fragment_show_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var viewModel: PerformanceListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_show_search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        etSearch = requireActivity().findViewById(R.id.et_search)
        viewModel = ViewModelProvider(requireActivity())[PerformanceListViewModel::class.java]

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isBlank()) {
                    recyclerView.visibility = View.GONE
                } else {
                    viewModel.performances.observe(viewLifecycleOwner) { list ->
                        val filtered = list.filter { it.title.contains(query, ignoreCase = true) }
                        recyclerView.adapter = ShowAdapter(filtered)
                        recyclerView.visibility = if (filtered.isEmpty()) View.GONE else View.VISIBLE
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
