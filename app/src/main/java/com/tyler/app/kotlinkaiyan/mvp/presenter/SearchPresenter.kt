package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.orhanobut.logger.Logger
import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.SearchContract
import com.tyler.app.kotlinkaiyan.mvp.model.SearchModel
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/9  10:42.
 * @描述  ${搜索页Presenter}.
 */
class SearchPresenter : BasePresenter<SearchContract.View>(), SearchContract.Presenter {

    private val mSearchModel by lazy { SearchModel() }
    private var mNextUrl: String? = null

    override fun addHistorySearchData(word: String) {
        checkViewAttach()
        if (mSearchModel.addHistorySearchData(word)) {
            requestHistorySearchData()
        }
    }

    override fun requestHistorySearchData() {
        checkViewAttach()
        val searchHistoryData = mSearchModel.getSearchHistoryData()
        mView?.setHistorySearchData(searchHistoryData)
    }

    override fun clearSearchHistory() {
        checkViewAttach()
        if (mSearchModel.clearSearchHistory()) {
            requestHistorySearchData()
        }
    }

    override fun requestHotSearchData() {
        checkViewAttach()
        addSubscription(
            disposable = mSearchModel.requestHotSearchWords()
                .subscribe({
                    mView?.apply {
                        dismissLoadding()
                        setHotSearchData(it)
                    }
                }, {
                    mView?.apply {
                        dismissLoadding()
                        showError(ExceptionHandler.handException(it))
                    }
                })
        )
    }

    override fun requestSearchResultData(tag: String) {
        checkViewAttach()
        addHistorySearchData(tag)
        val disposable = mSearchModel.requestSearchResultData(tag)
            ?.subscribe({
                mView?.apply {
                    dismissLoadding()
                    mNextUrl = it.nextPageUrl
                    val itemList = it.itemList
                    itemList.filter { item -> item.type != "video" }
                        .forEach { item -> itemList.remove(item) }
                    setSearchResultData(itemList, it.total)
                }
            }, {
                mView?.apply {
                    dismissLoadding()
                    showError(ExceptionHandler.handException(it))
                    loadMoreFail()
                }
            })
        if (disposable != null) {
            addSubscription(disposable)
        }
    }

    override fun requestMoreSearchResultData() {
        checkViewAttach()
        if (mNextUrl.isNullOrEmpty()) {
            mView?.loadMoreEnd()
        } else {
            val disposable = mNextUrl?.let {
                mSearchModel.requestMoreSearchResultData(it)
                    ?.subscribe({
                        mView?.apply {
                            Logger.d("nextUrl : $mNextUrl")
                            dismissLoadding()
                            mNextUrl = it.nextPageUrl
                            val itemList = it.itemList
                            itemList.filter { item -> item.type != "video" }
                                .forEach { item -> itemList.remove(item) }
                            setMoreResultData(itemList)
                        }
                    }, {
                        mView?.apply {
                            dismissLoadding()
                            showError(ExceptionHandler.handException(it))
                            loadMoreFail()
                        }
                    })
            }
            if (disposable != null) {
                addSubscription(disposable)
            }
        }
    }
}