package com.tyler.app.kotlinkaiyan.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:18.
 * @描述  ${presenter基类}.
 */
open class BasePresenter<V : IView> : IPresenter<V> {

    var mView: V? = null
    private var compositeDisposable = CompositeDisposable()

    override fun attachView(view: V) {
        this.mView = view
    }

    override fun detachView() {
        this.mView = null
        //取消订阅
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

    fun checkViewAttach() {
        if (mView == null) {
            throw  ViewNotAttachExpetion()
        }
    }

    /**
     * 添加订阅
     */
    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private class ViewNotAttachExpetion : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}