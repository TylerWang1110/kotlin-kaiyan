package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.VideoDetailContract
import com.tyler.app.kotlinkaiyan.mvp.model.VideoDetailModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/16  16:54.
 * @描述  ${视频详情}.
 */
class VideoDetailPresenter : BasePresenter<VideoDetailContract.View>() {

    private val mVideoDetailModel by lazy { VideoDetailModel() }

    fun requestRelatedVideoList(id: Int) {
        checkViewAttach()

        val disposable = mVideoDetailModel.requestRelatedVideoList(id)
            ?.subscribe({
                mView?.dismissLoadding()
                mView?.setRelatedVideoData(it?.itemList)
            }, {
                mView?.dismissLoadding()
                mView?.showError(ExceptionHandler.handException(it))
            })
    }
}