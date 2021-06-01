package com.hera.bangkit.ui.main.profile.story

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.data.response.StoryResponse
import com.hera.bangkit.databinding.FragmentStoryBinding


class  StoryFragment : Fragment() {


    private val viewModel : ProfileStoryViewModel by activityViewModels()
    private var _binding: FragmentStoryBinding? = null
    private lateinit var adapter: StoryAdapter
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentStoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMyStory().observe(viewLifecycleOwner,::setList)
    }

    private fun setList(items: ArrayList<StoryEntity>) {
        with(binding){
            rvStoryProfile.layoutManager = LinearLayoutManager(requireContext())
            adapter = StoryAdapter(items)
            rvStoryProfile.adapter = adapter
            adapter.notifyDataSetChanged()
        }



    }

}