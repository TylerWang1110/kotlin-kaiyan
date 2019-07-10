package com.tyler.app.kotlinkaiyan.net

import com.google.gson.JsonParseException
import com.orhanobut.logger.Logger
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  9:40.
 * @描述  ${错误统一处理}.
 */
class ExceptionHandler {
    companion object {
        private val TAG = "ExceptionHandler"
        var mErrorCode = ErrorCode.ERROE_UNKNOWN
        var mErrorMsg = "请求失败,请稍后重试"

        fun handException(e: Throwable): String {
            e.printStackTrace()
            if (e is HttpException || e is SocketTimeoutException || e is ConnectException || e is UnknownHostException) {
                mErrorCode = ErrorCode.ERROE_NETWORK
                mErrorMsg = "网络连接异常"
            } else if (e is JSONException
                || e is JsonParseException
                || e is ParseException
            ) {
                mErrorCode = ErrorCode.ERROE_DATA
                mErrorMsg = "数据解析异常"
            } else if (e is IllegalArgumentException) {
                mErrorCode = ErrorCode.ERROE_SERVER
                mErrorMsg = "参数异常"
            } else {
                mErrorCode = ErrorCode.ERROE_UNKNOWN
                mErrorMsg = "未知错误"
            }
            e.message?.let { Logger.e(TAG, it) }
            return mErrorMsg
        }
    }
}