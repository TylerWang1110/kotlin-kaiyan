package com.tyler.app.kotlinkaiyan.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.classic.common.MultipleStatusView
import com.tbruyelle.rxpermissions2.RxPermissions
import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.R

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/4  14:01.
 * @描述  ${Activity基类}.
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val mRxPermissions: RxPermissions = RxPermissions(this)
    /**
     * Application
     */
    protected var mBaseApp: BaseApp? = null
    /**
     * context
     */
    protected val mActivity: AppCompatActivity = this
    /**
     * 多状态view
     */
    protected var mRootStatusView: MultipleStatusView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Animation_RightInRightOutActivity)
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        mBaseApp = application as BaseApp?
        mBaseApp?.addActivity(this)
        initData()
        initView()
        start()
        mRootStatusView?.setOnClickListener { reStart() }
    }

    override fun onDestroy() {
        super.onDestroy()
        mBaseApp?.removeActivity(this)
    }

    /**
     * 布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化数据
     */
    abstract fun initData()

    /**
     * 初始化view
     */
    abstract fun initView()

    /**
     * 开始请求数据
     */
    abstract fun start()

    /**
     * 重新请求数据
     */
    protected fun reStart() {
        start()
    }

}