package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.CategoryDetailContract
import com.tyler.app.kotlinkaiyan.mvp.model.CategoryDetailModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  13:28.
 * @描述  ${分类详情}.
 */
class CategoryDetailPresenter : BasePresenter<CategoryDetailContract.View>(), CategoryDetailContract.Presenter {

    private val mCategoryDetailModel by lazy { CategoryDetailModel() }
    private var mNextUrl: String? = null

    override fun requestVideoListData(categoryId: Int) {
        checkViewAttach()
        mView?.showLoadding()
        val disposable = mCategoryDetailModel.requestVideoData(categoryId)?.subscribe({
            mView?.dismissLoadding()
            mNextUrl = it.nextPageUrl
            mView?.setVideoData(it.itemList)
        }, {
            mView?.dismissLoadding()
            mView?.showError(ExceptionHandler.handException(it))
        })

        if (disposable != null) {
            addSubscription(disposable)
        }
    }

    override fun requestMoreVideoData(nextUrl: String) {
        checkViewAttach()
        mView?.showLoadding()
        if (mNextUrl.isNullOrEmpty()) {
            mView?.loadMoreEnd()
        } else {
            mNextUrl?.let {
                val disposable = mCategoryDetailModel.requestMoreVideoData(it)?.subscribe({
                    mView?.dismissLoadding()
                    mNextUrl = it.nextPageUrl
                    mView?.setMoreVideoData(it.itemList)
                }, {
                    mView?.dismissLoadding()
                    mView?.loadMoreFail()
                    mView?.showError(ExceptionHandler.handException(it))
                })

                if (disposable != null) {
                    addSubscription(disposable)
                }
            }
        }
    }


}