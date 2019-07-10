package com.tyler.app.kotlinkaiyan.mvp.model

import com.google.gson.Gson
import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.model.bean.SearchHistoryData
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import com.tyler.app.kotlinkaiyan.util.Constant
import com.tyler.app.kotlinkaiyan.util.SPUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/9  10:42.
 * @描述  ${搜索页面Model}.
 */
class SearchModel {

    /**
     * 更新搜索历史
     */
    fun addHistorySearchData(word: String): Boolean {
        val searchHistory: String = SPUtils[BaseApp.context, Constant.SPConfig.SEARCH_HISTORY_WORDS, ""] as String
        val gson = Gson()
        var searchHistoryData = gson.fromJson(searchHistory, SearchHistoryData::class.java)
        if (searchHistoryData == null) {
            searchHistoryData = SearchHistoryData(arrayListOf())
        }
        //最多保存10个历史记录
        val words: ArrayList<String> = searchHistoryData.words
        if (words.contains(word)) {
            return false
        }
        if (words.size >= 10) {
            words.removeAt(0)
            words.add(word)
        } else {
            words.add(word)
        }
        val jsonStr = gson.toJson(SearchHistoryData(words))
        SPUtils.put(BaseApp.context, Constant.SPConfig.SEARCH_HISTORY_WORDS, jsonStr)
        return true
    }

    /**
     * 查询搜索历史
     */
    fun getSearchHistoryData(): ArrayList<String>? {
        val searchHistory: String = SPUtils[BaseApp.context, Constant.SPConfig.SEARCH_HISTORY_WORDS, ""] as String
        val gson = Gson()
        val searchHistoryData = gson.fromJson(searchHistory, SearchHistoryData::class.java) ?: null
        if (searchHistoryData != null) {
            return searchHistoryData.words
        } else {
            return null
        }
    }

    /**
     * 清空搜索历史
     */
    fun clearSearchHistory(): Boolean {
        SPUtils.put(BaseApp.context, Constant.SPConfig.SEARCH_HISTORY_WORDS, "")
        return true
    }

    /**
     * 获取搜索结果
     */
    fun requestSearchResultData(tag: String): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getSearchData(tag).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取下一页搜索结果
     */
    fun requestMoreSearchResultData(nexUrl: String): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getIssueListData(nexUrl).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取热门搜索词
     */
    fun requestHotSearchWords(): Observable<ArrayList<String>> {
        return RetrofitManager.service.getHotSearchWords().compose(SchedulerUtils.ioToMain())
    }
}