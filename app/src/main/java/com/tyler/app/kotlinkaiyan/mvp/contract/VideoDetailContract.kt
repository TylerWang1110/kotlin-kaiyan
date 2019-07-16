package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/16  16:55.
 * @描述  ${视频详情}.
 */
class VideoDetailContract {

    interface View : IView {

        fun setRelatedVideoData(itemList: ArrayList<HomeBean.Issue.Item>?)

        fun playVideo()

        fun showError(msg: String)
    }

    interface Presenter : IPresenter<View> {
        fun requestRelatedVideoList()
    }
}