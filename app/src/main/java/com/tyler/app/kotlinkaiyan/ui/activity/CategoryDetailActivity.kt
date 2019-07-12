package com.tyler.app.kotlinkaiyan.ui.activity

import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.mvp.contract.CategoryDetailContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.CategoryDetailPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.util.StatusBarUtils
import kotlinx.android.synthetic.main.activity_category_detail.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  13:37.
 * @描述  ${分类详情}.
 */
class CategoryDetailActivity : BaseActivity(), CategoryDetailContract.View {

    companion object {
        const val BUNDLE_CATEGORY_ID = "bundle_category_id"
    }

    private val mPresenter by lazy { CategoryDetailPresenter() }

    private var mCategoryId = 0

    override fun layoutId(): Int {
        return R.layout.activity_category_detail
    }

    override fun initData() {
        mCategoryId = intent.getIntExtra(BUNDLE_CATEGORY_ID, 0)
    }

    override fun initView() {
        mPresenter.attachView(this)
        //设置状态栏透明
        StatusBarUtils.darkMode(this)
        StatusBarUtils.setPaddingSmart(this, srl_category_detail)

    }

    override fun start() {
        mPresenter.requestVideoListData(mCategoryId)
    }

    override fun setVideoData(itemList: ArrayList<HomeBean.Issue.Item>) {

    }

    override fun setMoreVideoData(itemList: ArrayList<HomeBean.Issue.Item>) {

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