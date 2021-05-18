package com.hera.bangkit.ui.main.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import com.google.android.material.tabs.TabLayoutMediator
import com.hera.bangkit.R
import com.hera.bangkit.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
//    companion object {
//        @StringRes
//        private val TAB_TITLES = intArrayOf(
//            R.string.tab_1,
//            R.string.tab_2
//        )
//    }

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


            viewPager.adapter =  ProfileViewPagerAdapter(this@ProfileFragment)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}