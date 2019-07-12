package com.tyler.app.kotlinkaiyan.ui.fragment

import android.support.v7.widget.GridLayoutManager
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.mvp.contract.CategoryContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.CategoryBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.CategoryPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.adapter.CategoryListAdapter
import kotlinx.android.synthetic.main.fragment_category.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:01.
 * @描述  ${分类页}.
 */
class CategoryFragment : BaseFragment(), CategoryContract.View {

    private val mPresenter by lazy { CategoryPresenter() }
    private val mAdapter by lazy { CategoryListAdapter() }
    private val mLayoutManager by lazy { GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false) }

    companion object {
        fun getInstance(): CategoryFragment {
            return CategoryFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_category
    }

    override fun initView() {
        mPresenter.attachView(this)
        srl_category.setOnRefreshListener { start() }
        mh_category.setColorSchemeResources(R.color.text_color_black)
        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
        mAdapter.setEnableLoadMore(false)
        rv_category.adapter = mAdapter
        rv_category.layoutManager = mLayoutManager
    }

    override fun start() {
        mPresenter.requestCategoryData()
    }

    override fun setCategoryData(categoryDataList: ArrayList<CategoryBean>) {
        mAdapter.setNewData(categoryDataList)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {
        srl_category.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}