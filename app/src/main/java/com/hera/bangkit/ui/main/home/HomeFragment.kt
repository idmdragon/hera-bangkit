package com.hera.bangkit.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.R
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()
    private lateinit var adapter: HomeAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStoryList().observe(viewLifecycleOwner, ::setStoryList)
    }

    private fun setStoryList(items: ArrayList<StoryEntity>) {
        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter(items)
        adapter.notifyDataSetChanged()
        binding.rvHome.adapter = adapter

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}