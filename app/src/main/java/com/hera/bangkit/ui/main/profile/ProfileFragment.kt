package com.hera.bangkit.ui.main.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentProfileBinding
import com.hera.bangkit.ui.main.profile.setting.SettingActivity


class ProfileFragment : Fragment() {

    companion object {

        private val tabIcons = intArrayOf(
                R.drawable.ic_story_profile_active,
                R.drawable.ic_report_profile_active,
        )
    }
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding){

            Glide.with(requireActivity())
                .load("https://image.flaticon.com/icons/png/512/194/194938.png")
                .into(ivProfil)

            tvFullName.text = "Ilham Dwi Muchlison"
            tvUsername.text = "Idmdragon16"
            btnSetting.setOnClickListener {
                startActivity(Intent(requireContext(), SettingActivity::class.java))
            }

            viewPager.adapter =  ProfileViewPagerAdapter(this@ProfileFragment)
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = null
                tab.setIcon(tabIcons[position])

            }.attach()


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}