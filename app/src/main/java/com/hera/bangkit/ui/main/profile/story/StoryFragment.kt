package com.hera.bangkit.ui.main.profile.story

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.FragmentStoryBinding


class StoryFragment : Fragment() {

    companion object{
        private const val UID = "uid"
        fun newInstance(uid:String): StoryFragment {
            val fragment = StoryFragment()
            val bundle = Bundle()
            bundle.putString(UID,uid)
            fragment.arguments = bundle
            return fragment
        }
    }
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
        val uid = arguments?.getString(UID)
        if (uid != null) {
            viewModel.getMyStory(uid).observe(viewLifecycleOwner,::setList)
        }
    }

    private fun setList(items: ArrayList<StoryEntity>) {
        with(binding){
            Log.d("StoryFragment","isi setList $items")
            rvStoryProfile.layoutManager = LinearLayoutManager(requireContext())
            adapter = StoryAdapter(items,viewModel)
            rvStoryProfile.adapter = adapter
            adapter.notifyDataSetChanged()
        }



    }

}