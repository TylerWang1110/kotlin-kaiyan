package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:18.
 * @描述  ${关注列表}.
 */
class FollowModel {

    /**
     * 请求关注列表数据
     */
    fun requestFollowListData(): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getFollowListData().compose(SchedulerUtils.ioToMain())
    }

    /**
     *请求下一页数据
     */
    fun requestMoreFollowData(nexUrl: String): Observable<HomeBean.Issue> {
        return RetrofitManager.service.getIssueListData(nexUrl).compose(SchedulerUtils.ioToMain())
    }
}