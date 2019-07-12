package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.RankListContract
import com.tyler.app.kotlinkaiyan.mvp.model.RankListModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  11:43.
 * @描述  ${排行榜}.
 */
class RankListPresenter : BasePresenter<RankListContract.View>(), RankListContract.Presenter {

    private val mRankListModel by lazy { RankListModel() }

    override fun requestRankListData(strategy: String) {
        checkViewAttach()
        mView?.showLoadding()
        val disposable = mRankListModel.requestRankListData(strategy)
                ?.subscribe({
                    mView?.dismissLoadding()
                    mView?.setRankListData(it.itemList)
                }, {
                    mView?.dismissLoadding()
                    mView?.showError(ExceptionHandler.handException(it))
                })

        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}