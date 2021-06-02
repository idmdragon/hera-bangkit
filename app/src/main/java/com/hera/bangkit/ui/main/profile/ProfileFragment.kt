package com.hera.bangkit.ui.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.hera.bangkit.R
import com.hera.bangkit.data.response.UserResponse
import com.hera.bangkit.databinding.FragmentProfileBinding
import com.hera.bangkit.ui.main.profile.setting.SettingActivity
import com.idm.moviedb.data.source.remote.RemoteResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {

        private val tabIcons = intArrayOf(
                R.drawable.ic_story_profile_active,
                R.drawable.ic_report_profile_active,
        )
    }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val firebaseAuth = FirebaseAuth.getInstance()
    private val viewModel : ProfileViewModel by activityViewModels()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uid = firebaseAuth.currentUser?.uid
        uid?.let { viewModel.getUser(it).observe(viewLifecycleOwner,::setProfile) }
        with(binding){

            btnSetting.setOnClickListener {
                startActivity(Intent(requireContext(), SettingActivity::class.java))
            }
        }
    }

    private fun setProfile(remoteResponse: RemoteResponse<UserResponse>) {
        remoteResponse.body.let {
            with(binding){
                Glide.with(requireActivity())
                    .load(it.avatar)
                    .into(ivProfil)
                tvFullName.text = it.fullName
                tvUsername.text = it.username

                val profileViewPagerAdapter =  ProfileViewPagerAdapter(this@ProfileFragment)
                profileViewPagerAdapter.fullname = it.fullName
                profileViewPagerAdapter.userId = it.uid
                viewPager.adapter = profileViewPagerAdapter
                TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                    tab.text = null
                    tab.setIcon(tabIcons[position])
                }.attach()
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}