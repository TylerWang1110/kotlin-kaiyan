package com.tyler.app.kotlinkaiyan.util

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import com.orhanobut.logger.Logger

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/4  15:59.
 * @描述  ${屏幕相关工具类}.
 */
class UIUtils {

    companion object {


        /**
         *全屏
         */
        fun fullScreen(activity: Activity, isFull: Boolean) {
            if (isFull) {
                try {
                    activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    activity.window.setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                    )
                } catch (e: Exception) {
                    Logger.e(e.localizedMessage)
                }
            } else {
                try {
                    activity.requestWindowFeature(Window.FEATURE_CUSTOM_TITLE)
                    activity.window.setFlags(
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN
                    )
                } catch (e: Exception) {
                    Logger.e(e.localizedMessage)
                }
            }
        }
    }

    fun runOnUiThread(){

    }
}