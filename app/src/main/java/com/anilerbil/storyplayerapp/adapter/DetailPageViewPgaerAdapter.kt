package com.anilerbil.storyplayerapp.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.widget.ViewPager2
import com.anilerbil.storyplayerapp.Stories

class DetailPageViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager){

    private val fragmentList: MutableList<Fragment> = ArrayList()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment) {
        fragmentList.add(fragment)
    }

    fun destroyCurrentFragment(fragment: Fragment){
        fragmentList.remove(fragment)
    }
}