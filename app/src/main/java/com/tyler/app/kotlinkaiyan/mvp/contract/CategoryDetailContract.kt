package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  13:15.
 * @描述  ${分类详情}.
 */
class CategoryDetailContract {

    interface View : IView {

        fun setVideoData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun setMoreVideoData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String)

        fun loadMoreFail()

        fun loadMoreEnd()
    }

    interface Presenter : IPresenter<View> {

        fun requestVideoListData(categoryId: Int)

        fun requestMoreVideoData(nextUrl: String)
    }


}