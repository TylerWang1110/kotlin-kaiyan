package com.tyler.app.kotlinkaiyan.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.classic.common.MultipleStatusView

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  10:35.
 * @描述  ${Fragment基类(懒加载)}.
 */
abstract class BaseFragment : Fragment() {
    /**
     * View是否已初始化
     */
    private var mIsViewCreate: Boolean = false
    /**
     * 数据是否已加载过
     */
    private var mHasLoadData: Boolean = false
    /**
     * 多状态view
     */
    protected var mRootStatusView: MultipleStatusView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(layoutId(), null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mIsViewCreate = true
        initView()
        lazyLoad()
        mRootStatusView?.setOnClickListener { reLoad() }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoad()
        }
    }

    private fun lazyLoad() {
        //当前界面对用户显示 && view已初始化 && 数据未加载过
        if (userVisibleHint && mIsViewCreate && !mHasLoadData) {
            start()
            mHasLoadData = true
        }
    }

    protected fun reLoad() {
        lazyLoad()
    }

    /**
     * 布局
     */
    abstract fun layoutId(): Int

    /**
     * 初始化view相关
     */
    abstract fun initView()

    /**
     * 加载数据
     */
    abstract fun start()
}