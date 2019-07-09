package com.tyler.app.kotlinkaiyan.util

import com.tyler.app.kotlinkaiyan.BaseApp

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/4  17:30.
 * @描述  ${系统相关}.
 */
class SysUtils {

    companion object {


        fun getAppVerName(): String {
            val context = BaseApp.context
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionName
        }

        fun getAppVerCode(): Int {
            val context = BaseApp.context
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            return packageInfo.versionCode
        }
    }

}