package com.hera.bangkit.ui.main.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hera.bangkit.ui.main.profile.report.ReportFragment
import com.hera.bangkit.ui.main.profile.story.StoryFragment


class ProfileViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {

    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = StoryFragment()
            1 -> fragment = ReportFragment()
        }
        return fragment as Fragment
    }




}
