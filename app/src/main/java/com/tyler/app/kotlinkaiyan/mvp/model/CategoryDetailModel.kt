package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  13:26.
 * @描述  ${分类详情}.
 */
class CategoryDetailModel {

    fun requestVideoData(categoryId: Int): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getCategoryDetail(categoryId).compose(SchedulerUtils.ioToMain())
    }

    fun requestMoreVideoData(nexUrl: String): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getIssueListData(nexUrl).compose(SchedulerUtils.ioToMain())
    }
}