package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:25.
 * @描述  ${首页model}.
 */
class MainHomeModel {

    /**
     * 请求每日精选数据
     */
    fun requestDailyFirstData(num: Int): Observable<HomeBean> {
        return RetrofitManager.service.getHomeFirstData(num).compose(SchedulerUtils.ioToMain())
    }

    /**
     * 请求每日精选数据
     */
    fun requestDailyNextData(url: String): Observable<HomeBean> {
        return RetrofitManager.service.getHomeNextData(url).compose(SchedulerUtils.ioToMain())
    }


}