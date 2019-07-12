package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.CategoryContract
import com.tyler.app.kotlinkaiyan.mvp.model.CategoryModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/11  16:32.
 * @描述  ${分类}.
 */
class CategoryPresenter : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    private val mCategoryModel by lazy { CategoryModel() }

    override fun requestCategoryData() {
        checkViewAttach()
        mView?.showLoadding()
        val disposable = mCategoryModel.requestCategoryData().subscribe({
            mView?.dismissLoadding()
            mView?.setCategoryData(it)
        }, {
            mView?.dismissLoadding()
            mView?.showError(ExceptionHandler.handException(it))
        })
        addSubscription(disposable)
    }

}