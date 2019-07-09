package com.tyler.app.kotlinkaiyan.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  14:01.
 * @描述  ${日期处理工具类}.
 */
class DateUtil {
    companion object {

        /**
         * 格式化时间
         */
        @SuppressLint("SimpleDateFormat")
        fun getDateStr(timeMillis: Long, formatStr: String): String {
            val str: String
            val date = Date(timeMillis)
            val format = SimpleDateFormat(formatStr)
            str = format.format(date)
            return str
        }

        fun formatDate(timeMillis: Long, format: SimpleDateFormat): String {
            return format.format(Date(timeMillis))
        }

        /**
         * 视频时长转换
         * @param duration 秒
         */
        fun getVideoDuration(duration: Int): String {
            var str = ""
            var hour = 0
            var min = 0
            var second = 0
            if (duration <= 0) {
                str = "00:00"
            } else {
                min = duration / 60
                if (min < 60) {
                    second = duration % 60
                    str = unitFormat(min) + ":" + unitFormat(second)
                } else {
                    hour = min / 60
                    if (hour > 99) {
                        str = "99:59:59"
                    } else {
                        min %= 60
                        second = duration - hour * 3600 - min * 60
                        str = unitFormat(hour) + ":" + unitFormat(min) + ":" + unitFormat(second)
                    }
                }
            }
            return str
        }

        private fun unitFormat(time: Int): String {
            return if (time in 0..9)
                "0" + Integer.toString(time)
            else
                "" + time
        }
    }
}