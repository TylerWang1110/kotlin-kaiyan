package com.tyler.app.kotlinkaiyan

import android.content.Context
import android.widget.Toast

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  10:10.
 * @描述  ${扩展工具类}.
 */

fun showToast(msg: String) {
    Toast.makeText(BaseApp.context, msg, Toast.LENGTH_SHORT).show()
}

fun Context.dp2px(dp: Float): Int {
    val scale = resources.displayMetrics.density
    return (dp * scale + 0.5f).toInt()
}

fun Context.px2dp(px: Float): Int {
    val scale = resources.displayMetrics.density
    return (px / scale + 0.5f).toInt()
}
