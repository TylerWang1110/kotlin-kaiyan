package com.tyler.app.kotlinkaiyan.rx

/**
 * Created by xuhao on 2017/11/17.
 * desc:
 */

object SchedulerUtils {

    fun <T> ioToMain(): IoMainScheduler<T> {
        return IoMainScheduler()
    }
}
