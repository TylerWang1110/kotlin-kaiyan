package com.tyler.app.kotlinkaiyan.net.api

import com.tyler.app.kotlinkaiyan.mvp.model.bean.CategoryBean
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:28.
 * @描述  ${接口}.
 */
interface ApiService {

    /**
     * 精选首页
     */
    @GET("v2/feed?")
    fun getHomeFirstData(@Query("num") num: Int): Observable<HomeBean>

    /**
     * 精选下一页
     */
    @GET
    fun getHomeNextData(@Url url: String): Observable<HomeBean>

    /**
     * 搜索(默认每页10, 从0开始)
     * @param num 数量
     * @param start 起始index
     * @param query 关键词
     */
    @GET("v1/search?num=10&start=0")
    fun getSearchData(@Query("query") query: String): Observable<HomeBean.Issue>

    /**
     * 获取视频信息列表
     */
    @GET
    fun getIssueListData(@Url url: String): Observable<HomeBean.Issue>

    /**
     * 热门搜索
     */
    @GET("v3/queries/hot")
    fun getHotSearchWords(): Observable<ArrayList<String>>

    /**
     * 关注(推荐) 数据
     */
    @GET("v4/tabs/follow")
    fun getFollowListData(): Observable<HomeBean.Issue>

    /**
     * 分类
     */
    @GET("v4/categories")
    fun getCategory(): Observable<ArrayList<CategoryBean>>

    /**
     * 分类详情(起始)
     */
    @GET("v4/categories/videoList?")
    fun getCategoryDetail(@Query("id") id: Int): Observable<HomeBean.Issue>

    /**
     * 排行
     * @param strategy weekly-周 monthly-月 historical-历史总排行
     */
    @GET("v4/rankList/videos?")
    fun getVideoRankList(@Query("strategy") strategy: String): Observable<HomeBean.Issue>
}