package com.tyler.app.kotlinkaiyan.mvp.contract

import com.tyler.app.kotlinkaiyan.base.IPresenter
import com.tyler.app.kotlinkaiyan.base.IView
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/9  10:33.
 * @描述  ${搜索页}.
 */
class SearchContract {

    interface View : IView {

        fun setHistorySearchData(historyData: ArrayList<String>?)

        fun setHotSearchData(hotWords: ArrayList<String>?)

        fun setSearchResultData(itemList: ArrayList<HomeBean.Issue.Item>, total: Int)

        fun setMoreResultData(itemList: ArrayList<HomeBean.Issue.Item>)

        fun showError(msg: String)

        fun loadMoreFail()

        fun loadMoreEnd()
    }

    interface Presenter : IPresenter<View> {

        fun addHistorySearchData(word: String)

        fun requestHistorySearchData()

        fun clearSearchHistory()

        fun requestHotSearchData()

        fun requestSearchResultData(tag: String)

        fun requestMoreSearchResultData()
    }
}