package com.tyler.app.kotlinkaiyan.util

import android.content.Context

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/9  10:46.
 * @描述  ${sp操作相关}.
 */
class SPUtils private constructor() {

    companion object {
        /**
         * 保存在手机里面的sp文件名
         */
        val FILE_NAME = "kaiyan_config"

        /**
         * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
         *
         * @param context
         * @param key
         * @param data
         */
        fun put(context: Context, key: String, data: Any?) {
            if (data == null) {
                return
            }
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            val editor = sp.edit()

            when (data) {
                is String -> editor.putString(key, data as String?)
                is Int -> editor.putInt(key, (data as Int?)!!)
                is Boolean -> editor.putBoolean(key, (data as Boolean?)!!)
                is Float -> editor.putFloat(key, (data as Float?)!!)
                is Long -> editor.putLong(key, (data as Long?)!!)
                else -> editor.putString(key, data.toString())
            }
            editor.apply()
        }

        /**
         * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
         *
         * @param context
         * @param key
         * @param defaultData
         * @return
         */
        operator fun get(context: Context, key: String, defaultData: Any): Any? {
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            return when (defaultData) {
                is String -> sp.getString(key, defaultData)
                is Int -> sp.getInt(key, defaultData)
                is Boolean -> sp.getBoolean(key, defaultData)
                is Float -> sp.getFloat(key, defaultData)
                is Long -> sp.getLong(key, defaultData)
                else -> null
            }
        }

        /**
         * 移除某个key值已经对应的值
         *
         * @param context
         * @param key
         */
        fun remove(context: Context, key: String) {
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            val editor = sp.edit()
            editor.remove(key)
            editor.apply()
        }

        /**
         * 清除所有数据
         *
         * @param context
         */
        fun clear(context: Context) {
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            val editor = sp.edit()
            editor.clear()
            editor.apply()
        }

        /**
         * 查询某个key是否已经存在
         *
         * @param context
         * @param key
         * @return
         */
        fun contains(context: Context, key: String): Boolean {
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            return sp.contains(key)
        }

        /**
         * 返回所有的键值对
         *
         * @param context
         * @return
         */
        fun getAll(context: Context): Map<String, *> {
            val sp = context.getSharedPreferences(
                FILE_NAME,
                Context.MODE_PRIVATE
            )
            return sp.all
        }
    }
}

