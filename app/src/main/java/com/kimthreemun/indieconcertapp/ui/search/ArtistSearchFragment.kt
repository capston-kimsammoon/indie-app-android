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
import com.kimthreemun.indieconcertapp.ui.artist.list.ArtistListViewModel

class ArtistSearchFragment : Fragment(R.layout.fragment_artist_search) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistListViewModel
    private lateinit var etSearch: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_artist_search)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel = ViewModelProvider(requireActivity())[ArtistListViewModel::class.java]

        etSearch = requireActivity().findViewById(R.id.et_search)

        etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString().trim()
                if (query.isBlank()) {
                    recyclerView.visibility = View.GONE
                } else {
                    viewModel.artists.observe(viewLifecycleOwner) { list ->
                        val filtered = list.filter { it.name.contains(query, ignoreCase = true) }
                        recyclerView.adapter = ArtistAdapter(filtered)
                        recyclerView.visibility = if (filtered.isEmpty()) View.GONE else View.VISIBLE
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
