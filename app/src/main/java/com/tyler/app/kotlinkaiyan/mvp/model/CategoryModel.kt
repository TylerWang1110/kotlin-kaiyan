package com.tyler.app.kotlinkaiyan.mvp.model

import com.tyler.app.kotlinkaiyan.mvp.model.bean.CategoryBean
import com.tyler.app.kotlinkaiyan.net.RetrofitManager
import com.tyler.app.kotlinkaiyan.rx.SchedulerUtils
import io.reactivex.Observable

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/11  16:31.
 * @描述  ${分类}.
 */
class CategoryModel {

    fun requestCategoryData(): Observable<ArrayList<CategoryBean>> {
        return RetrofitManager.service.getCateGory().compose(SchedulerUtils.ioToMain())
    }
}
