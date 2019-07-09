package com.tyler.app.kotlinkaiyan.base

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  10:59.
 * @描述  ${BaseView}.
 */
interface IView {
    /**
     * 显示加载
     */
    fun showLoadding()

    /**
     * 取消显示加载
     */
    fun dismissLoadding()
}