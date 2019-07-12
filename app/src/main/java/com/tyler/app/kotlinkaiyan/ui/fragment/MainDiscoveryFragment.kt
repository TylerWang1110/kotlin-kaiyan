package com.tyler.app.kotlinkaiyan.ui.fragment

import android.support.v4.app.Fragment
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.ui.adapter.BaseFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_main_discovery.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:41.
 * @描述  ${发现}.
 */
class MainDiscoveryFragment : BaseFragment() {

    private val mTitles = arrayOf("关注", "分类")

    companion object {
        fun getInstance(): MainDiscoveryFragment {
            return MainDiscoveryFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main_discovery
    }

    override fun initData() {

    }

    override fun initView() {
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(FollowFragment.getInstance())
        fragmentList.add(CategoryFragment.getInstance())
        vp_main_discovery.offscreenPageLimit = mTitles.size
        vp_main_discovery.adapter = BaseFragmentPagerAdapter(childFragmentManager, fragmentList, mTitles)
        stl_main_discovery.setViewPager(vp_main_discovery, mTitles, activity, fragmentList)
    }

    override fun start() {

    }
}