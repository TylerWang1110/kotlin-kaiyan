package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/16  17:05.
 * @描述  ${视频详情]}.
 */
class VideoDetailModel {

    fun requestRelatedVideoList(id: Int): Observable<HomeBean.Issue>? {
        return RetrofitManager.service.getRelatedVideo(id).compose(SchedulerUtils.ioToMain())
    }
}