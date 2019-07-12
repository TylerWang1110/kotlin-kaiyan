package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.CategoryBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/11  16:28.
 * @描述  ${分类页面}.
 */
class CategoryContract {

    interface View : IView {

        fun setCategoryData(categoryDataList: ArrayList<CategoryBean>)

        fun showError(msg: String)
    }

    interface Presenter : IPresenter<View> {

        fun requestCategoryData()

    }
}