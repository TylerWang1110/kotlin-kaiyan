package com.tyler.app.kotlinkaiyan.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  14:54.
 * @描述  ${ViewPager Fragment BaseAdapter}.
 */
class BaseFragmentPagerAdapter(fm: FragmentManager,
                               var fragmentList: ArrayList<Fragment>,
                               var titles: Array<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(p0: Int): Fragment {
        return fragmentList[p0]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }
}