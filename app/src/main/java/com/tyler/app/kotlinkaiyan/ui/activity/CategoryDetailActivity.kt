package com.tyler.app.kotlinkaiyan.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.tyler.app.kotlinkaiyan.BaseApp.Companion.context
import com.tyler.app.kotlinkaiyan.GlideApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.mvp.contract.CategoryDetailContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.CategoryDetailPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.adapter.SearchResultListAdapter
import com.tyler.app.kotlinkaiyan.util.StatusBarUtils
import com.tyler.app.kotlinkaiyan.view.RecyclerViewDivider
import kotlinx.android.synthetic.main.activity_category_detail.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  13:37.
 * @描述  ${分类详情}.
 */
class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.View {

    companion object {
        const val BUNDLE_CATEGORY_ID = "bundle_category_id"
        const val BUNDLE_NAME = "bundle_name"
        const val BUNDLE_DESCRIPTION = "bundle_description"
        const val BUNDLE_HEADER_IMG = "bundle_header_img"
    }

    private val mPresenter by lazy { CategoryDetailPresenter() }
    private val mAdapter by lazy { SearchResultListAdapter() }
    private val mLayoutManager by lazy { LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false) }

    private var mCategoryId = 0
    private var mName = ""
    private var mDescription = ""
    private var mHeaderImg = ""

    override fun layoutId(): Int {
        return R.layout.activity_category_detail
    }

    override fun initData() {
        mCategoryId = intent.getIntExtra(BUNDLE_CATEGORY_ID, 0)
        mName = intent.getStringExtra(BUNDLE_NAME)
        mDescription = intent.getStringExtra(BUNDLE_DESCRIPTION)
        mHeaderImg = intent.getStringExtra(BUNDLE_HEADER_IMG)
    }

    override fun initView() {
        mPresenter.attachView(this)
        //设置状态栏透明
        StatusBarUtils.darkMode(this)
//        StatusBarUtils.setPaddingSmart(this, srl_category_detail)
        //设置头部
        tv_category_detail_title_name.text = mName
        tv_category_detail_header_name.text = mName
        tv_category_detail_header_description.text = mDescription
        GlideApp.with(mActivity)
            .load(mHeaderImg)
            .into(iv_category_detail_header)

        srl_category_detail.setOnRefreshListener { start() }
        mh_category_detail.setColorSchemeResources(R.color.text_color_black)
        mAdapter.setOnLoadMoreListener({
            mPresenter.requestMoreVideoData()
        }, rv_category_detail)
        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
        rv_category_detail.adapter = mAdapter
        rv_category_detail.layoutManager = mLayoutManager
        rv_category_detail.addItemDecoration(
            RecyclerViewDivider(
                context,
                LinearLayoutManager.HORIZONTAL,
                1,
                resources.getColor(R.color.list_dividingLine)
            )
        )
    }

    override fun start() {
        mPresenter.requestVideoListData(mCategoryId)
    }

    override fun setVideoData(itemList: ArrayList<HomeBean.Issue.Item>) {
        mAdapter.setNewData(itemList)
    }

    override fun setMoreVideoData(itemList: ArrayList<HomeBean.Issue.Item>) {
        mAdapter.addData(itemList)
        mAdapter.loadMoreComplete()
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun loadMoreFail() {

    }

    override fun loadMoreEnd() {

    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}