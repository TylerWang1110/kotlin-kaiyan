package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  11:48.
 * @描述  ${排行榜}.
 */
class RankListModel {

    fun requestRankListData(strategy: String): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getVideoRankList(strategy).compose(SchedulerUtils.ioToMain())
    }
}