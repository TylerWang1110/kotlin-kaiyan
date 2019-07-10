package com.tyler.app.kotlinkaiyan

import android.app.Activity
import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/4  16:07.
 * @描述  ${App}.
 */
class BaseApp : Application() {

    private var mActivityList: MutableList<Activity> = arrayListOf()

    companion object {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = this.applicationContext
        init()
    }

    private fun init() {
        //chrome 调试
        Stetho.initializeWithDefaults(this)
        //初始化logger
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    fun addActivity(activity: Activity) {
        mActivityList.add(activity)
    }

    fun removeActivity(activity: Activity) {
        if (mActivityList.contains(activity)) {
            mActivityList.remove(activity)
            activity.finish()
        }
    }

    fun clearAllActivities() {
        mActivityList.forEach { it.finish() }
        mActivityList.clear()
    }
}