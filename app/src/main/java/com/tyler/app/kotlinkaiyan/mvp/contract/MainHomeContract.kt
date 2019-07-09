package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:03.
 * @描述  ${Home 契约类}.
 */
class MainHomeContract {

    interface View : IView {

        fun setFirstData(homeBean: HomeBean)

        fun setNextData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String)

        fun loadMoreFail()
    }

    interface Presenter : IPresenter<View> {

        fun requestFirstData(num: Int)

        fun requestNextData()
    }
}