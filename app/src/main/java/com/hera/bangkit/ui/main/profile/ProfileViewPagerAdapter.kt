package com.hera.bangkit.ui.main.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.hera.bangkit.ui.main.profile.report.ReportFragment
import com.hera.bangkit.ui.main.profile.story.StoryFragment


class ProfileViewPagerAdapter(fm: Fragment) : FragmentStateAdapter(fm) {

    var fullname : String? = null
    var userId : String? = null

    override fun getItemCount(): Int {
       return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = userId?.let { StoryFragment.newInstance(it) }
            1 -> fragment = fullname?.let { ReportFragment.newInstance(it) }
        }
        return fragment as Fragment
    }




}
