package com.tyler.app.kotlinkaiyan.ui.fragment

import android.support.v4.app.Fragment
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.ui.adapter.BaseFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_main_hot.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:41.
 * @描述  ${热门}.
 */
class MainHotFragment : BaseFragment() {

    private val mTitles = arrayOf("周排行", "月排行", "总排行")


    companion object {
        fun getInstance(): MainHotFragment {
            return MainHotFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main_hot
    }

    override fun initData() {

    }

    override fun initView() {
        val fragmentList = arrayListOf<Fragment>()
        fragmentList.add(RankListFragment.getInstance(RankListFragment.RANK_TYPE_WEEKLY))
        fragmentList.add(RankListFragment.getInstance(RankListFragment.RANK_TYPE_MONTHLY))
        fragmentList.add(RankListFragment.getInstance(RankListFragment.RANK_TYPE_HISTORICAL))
        vp_main_hot.offscreenPageLimit = mTitles.size
        vp_main_hot.adapter = BaseFragmentPagerAdapter(childFragmentManager, fragmentList, mTitles)
        stl_main_hot.setViewPager(vp_main_hot, mTitles, activity, fragmentList)
    }

    override fun start() {
    }
}