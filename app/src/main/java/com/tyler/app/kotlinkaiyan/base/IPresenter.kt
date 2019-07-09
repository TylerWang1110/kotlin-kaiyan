package com.tyler.app.kotlinkaiyan.base

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  10:59.
 * @描述  ${Presenter}.
 */
interface IPresenter<V : IView> {
    /**
     * 关联view
     */
    fun attachView(view: V)

    /**
     * 取消关联view
     */
    fun detachView()
}