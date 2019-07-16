package com.tyler.app.kotlinkaiyan.ui.fragment

import android.content.Intent
import android.graphics.Typeface
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.mvp.contract.MainHomeContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.MainHomePresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.activity.SearchActivity
import com.tyler.app.kotlinkaiyan.ui.activity.VideoDetailActivity
import com.tyler.app.kotlinkaiyan.ui.adapter.HomeListAdapter
import com.tyler.app.kotlinkaiyan.util.DateUtils
import com.tyler.app.kotlinkaiyan.view.RecyclerViewDivider
import kotlinx.android.synthetic.main.fragment_main_home.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  10:35.
 * @描述  ${首页fragment}.
 */
class MainHomeFragment : BaseFragment(), MainHomeContract.View {

    private var mNum: Int = 1
    private var mIsRefresh = true

    private val mPresenter by lazy { MainHomePresenter() }
    private val mAdapter by lazy { HomeListAdapter() }
    private val mTypefaceTitle by lazy { Typeface.createFromAsset(BaseApp.context.assets, "fonts/Lobster-1.4.otf") }
    val mLayoutManger: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    companion object {
        fun getInstance(): MainHomeFragment {
            return MainHomeFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_main_home
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        //重新设置toolbar高度
        tv_main_home_title.typeface = mTypefaceTitle
        tv_main_home_title.text =
            DateUtils.formatDate(System.currentTimeMillis(), SimpleDateFormat("- MMM. dd -", Locale.ENGLISH))
        mh_main_home.setColorSchemeResources(R.color.text_color_black)
        srl_main_home.setOnRefreshListener {
            mIsRefresh = true
            start()
        }
        mAdapter.setPreLoadNumber(2)
        mAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            val item: HomeBean.Issue.Item = adapter.getItem(position) as HomeBean.Issue.Item
            val intent = Intent(context, VideoDetailActivity::class.java)
            intent.putExtra(VideoDetailActivity.BUNDLE_VIDEO_DATA, item.data)
            startActivity(intent)
        }
        mAdapter.setOnLoadMoreListener({
            mIsRefresh = false
            mPresenter.requestNextData()
        }, rv_main_home)
        rv_main_home.adapter = mAdapter
        rv_main_home.layoutManager = mLayoutManger
        rv_main_home.addItemDecoration(
            RecyclerViewDivider(
                context,
                LinearLayoutManager.HORIZONTAL,
                1,
                resources.getColor(R.color.list_dividingLine)
            )
        )
        rv_main_home.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstVisibleItemPosition = (mLayoutManger as LinearLayoutManager).findFirstVisibleItemPosition()
                if (mAdapter.data.size > 1) {
                    val itemList = mAdapter.data
                    val item = itemList[firstVisibleItemPosition]
                    tv_main_home_title.text =
                        DateUtils.formatDate(item.data.date, SimpleDateFormat("- MMM. dd -", Locale.ENGLISH))
                }
            }
        })

        iv_main_home_search.setOnClickListener { startActivity(Intent(activity, SearchActivity::class.java)) }
    }


    override fun start() {
        mPresenter.requestFirstData(mNum)
    }

    override fun setFirstData(homeBean: HomeBean) {
        msv_main_home.showContent()
        mAdapter.setNewData(homeBean.issueList[0].itemList)
        mAdapter.notifyDataSetChanged()
    }

    override fun setNextData(itemList: ArrayList<HomeBean.Issue.Item>) {
        mAdapter.addData(itemList)
        mAdapter.loadMoreComplete()
    }

    override fun loadMoreFail() {
        mAdapter.loadMoreFail()
    }

    override fun loadMoreEnd() {
        mAdapter.loadMoreEnd()
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun showLoadding() {
        if (mIsRefresh) {
            msv_main_home.showLoading()
        }
    }

    override fun dismissLoadding() {
        srl_main_home.finishRefresh()
        mIsRefresh = false
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}