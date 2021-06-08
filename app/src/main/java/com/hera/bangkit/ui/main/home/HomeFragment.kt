package com.hera.bangkit.ui.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.data.source.Resource
import com.hera.bangkit.data.source.local.entity.StoryEntity
import com.hera.bangkit.databinding.FragmentHomeBinding
import com.hera.bangkit.ui.main.profile.ProfileViewModel
import com.hera.bangkit.ui.viewmodel.StoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

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

        viewModel.getStoryList().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.progressbar.isVisible = true
                }
                is Resource.Success -> {
                    binding.progressbar.isVisible = false
                    it.data?.let { item ->
                        Log.d("HomeFragment","isi item $item")
                        adapter = HomeAdapter(viewModel, item as ArrayList<StoryEntity>)
                        adapter.notifyDataSetChanged()
                        binding.rvHome.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvHome.isVisible = true
                        adapter.notifyDataSetChanged()
                        binding.rvHome.adapter = adapter
                    }

                }
                is Resource.Error -> {
                    binding.progressbar.isVisible = false
                    Toast.makeText(
                        requireContext(),
                        "Error when Load a Data",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
        val uid = firebaseAuth.currentUser?.uid
        uid?.let { profilViewModel.getUser(it).observe(viewLifecycleOwner,{
            it.let { user ->
                binding.tvUsername.text = user.body.username
            }
        }) }
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}