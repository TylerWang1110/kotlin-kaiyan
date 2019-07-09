package com.tyler.app.kotlinkaiyan.ui.activity

import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.util.StatusBarUtil
import kotlinx.android.synthetic.main.activity_search.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  17:42.
 * @描述  ${搜索页}.
 */
class SearchActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    override fun initData() {

    }

    override fun initView() {
        //设置状态栏透明
        StatusBarUtil.darkMode(this)
        StatusBarUtil.setPaddingSmart(this, ll_title_search)
    }

    override fun start() {

    }
}