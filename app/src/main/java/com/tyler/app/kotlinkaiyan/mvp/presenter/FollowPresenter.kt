package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.FollowContract
import com.tyler.app.kotlinkaiyan.mvp.model.FollowModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:28.
 * @描述  ${关注 Presenter}.
 */
class FollowPresenter : BasePresenter<FollowContract.View>(), FollowContract.Presenter {

    private val mFollowModel by lazy { FollowModel() }
    private var mNextUrl: String? = null

    override fun requestFollowListData() {
        checkViewAttach()
        mView?.showLoadding()
        val disposable = mFollowModel.requestFollowListData().subscribe(
            {
                mNextUrl = it.nextPageUrl
                mView?.apply {
                    mView?.dismissLoadding()
                    mView?.setFollowListData(it)
                }
            }, {
                mView?.apply {
                    mView?.dismissLoadding()
                    mView?.showError(ExceptionHandler.handException(it))
                }
            })
        addSubscription(disposable)
    }

    override fun requestMoreFollowData() {
        checkViewAttach()
        if (mNextUrl.isNullOrEmpty()) {
            mView?.loadMoreEnd()
        } else {
            val disposable = mNextUrl?.let {
                mFollowModel.requestMoreFollowData(it).subscribe(
                    {
                        mView?.dismissLoadding()
                        mNextUrl = it.nextPageUrl
                        mView?.setMoreFollowListData(it)
                    }, {
                        mView?.dismissLoadding()
                        mView?.showError(ExceptionHandler.handException(it))
                    })
            }
            if (disposable != null) {
                addSubscription(disposable)
            }
        }
    }

}