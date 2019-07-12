package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  11:43.
 * @描述  ${排行榜}.
 */
class RankListContract {

    interface View : IView {

        fun setRankListData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String)
    }

    interface Presenter : IPresenter<View> {

        fun requestRankListData(strategy: String)

    }
}