package com.hera.bangkit.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.data.entity.StoryEntity
import com.hera.bangkit.databinding.FragmentHomeBinding
import com.hera.bangkit.ui.main.profile.ProfileViewModel
import com.hera.bangkit.ui.viewmodel.StoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: StoryViewModel by activityViewModels()
    private val profilViewModel: ProfileViewModel by activityViewModels()
    private lateinit var adapter: HomeAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStoryList().observe(viewLifecycleOwner, ::setStoryList)
        val uid = firebaseAuth.currentUser?.uid
        uid?.let { profilViewModel.getUser(it).observe(viewLifecycleOwner,{
            it.let { user ->
                binding.tvUsername.text = user.body.username
            }
        }) }
    }

    private fun setStoryList(items: ArrayList<StoryEntity>) {
        with(binding){
           rvHome.layoutManager = LinearLayoutManager(requireContext())
            progressbar.isVisible = false
            rvHome.isVisible = true
            adapter = HomeAdapter(viewModel,items)
            adapter.notifyDataSetChanged()
           rvHome.adapter = adapter
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}