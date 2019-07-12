package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:15.
 * @描述  ${关注页}.
 */
class FollowContract {

    interface View : IView {

        fun setFollowListData(issue: HomeBean.Issue)

        fun setMoreFollowListData(issue: HomeBean.Issue)

        fun showError(msg: String)

        fun loadMoreFail()

        fun loadMoreEnd()
    }

    interface Presenter : IPresenter<View> {

        fun requestFollowListData()

        fun requestMoreFollowData()
    }
}