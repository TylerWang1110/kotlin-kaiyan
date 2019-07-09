package com.tyler.app.kotlinkaiyan.net.api

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
}