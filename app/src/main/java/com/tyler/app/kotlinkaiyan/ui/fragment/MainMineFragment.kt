package com.tyler.app.kotlinkaiyan.ui.fragment

import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:41.
 * @描述  ${TODO}.
 */
class MainMineFragment : BaseFragment() {

    companion object {
        fun getInstance(): MainMineFragment {
            val fragment = MainMineFragment()
            return fragment
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main_mine
    }

    override fun initData() {

    }

    override fun initView() {
    }

    override fun start() {
    }
}