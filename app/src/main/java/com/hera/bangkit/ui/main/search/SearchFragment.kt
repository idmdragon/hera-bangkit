package com.hera.bangkit.ui.main.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.databinding.FragmentSearchBinding


class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by activityViewModels()
    private lateinit var adapter: HastagAdapter
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Hastag List
        binding.rvHastag.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getHastag().observe(viewLifecycleOwner, ::getHastagList)

        searchFunction()
    }

    private fun searchFunction() {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()) {
                    return true
                } else {
                    binding.searchLayout.visibility = View.VISIBLE
                    binding.tvSearchKeyword.text = query
                    binding.hastagLayout.visibility = View.GONE
                    binding.searchNotfound.visibility = View.VISIBLE
                    activity.apply {
//                        viewModel.searchItem(newText).observe(viewLifecycleOwner,::showRv)
                    }

                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return true
            }
        })
    }

    private fun getHastagList(items: ArrayList<HastagEntity>) {
        adapter = HastagAdapter(items)
        adapter.notifyDataSetChanged()
        binding.rvHastag.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}