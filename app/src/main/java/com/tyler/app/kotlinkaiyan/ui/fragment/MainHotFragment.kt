package com.tyler.app.kotlinkaiyan.ui.fragment

import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:41.
 * @描述  ${TODO}.
 */
class MainHotFragment : BaseFragment() {

    companion object {
        fun getInstance(): MainHotFragment {
            val fragment = MainHotFragment()
            return fragment
        }
    }


    override fun layoutId(): Int {
        return R.layout.fragment_main_hot
    }

    override fun initView() {
    }

    override fun start() {
    }
}