package com.tyler.app.kotlinkaiyan.ui.fragment

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.mvp.contract.FollowContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.FollowPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.activity.VideoDetailActivity
import com.tyler.app.kotlinkaiyan.ui.adapter.FollowListAdapter
import com.tyler.app.kotlinkaiyan.view.RecyclerViewDivider
import kotlinx.android.synthetic.main.fragment_follow_list.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:01.
 * @描述  ${关注}.
 */
class FollowFragment : BaseFragment(), FollowContract.View {

    private val mPresenter by lazy { FollowPresenter() }
    private val mAdapter by lazy { FollowListAdapter() }
    private val mLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) }

    companion object {
        fun getInstance(): FollowFragment {
            return FollowFragment()
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_follow_list
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        mh_follow_list.setColorSchemeResources(R.color.text_color_black)
        srl_follow_list.setOnRefreshListener {
            start()
        }
        rv_follow_list.layoutManager = mLayoutManager
        rv_follow_list.addItemDecoration(
            RecyclerViewDivider(
                context,
                LinearLayoutManager.HORIZONTAL,
                1,
                resources.getColor(R.color.list_dividingLine)
            )
        )
        mAdapter.setPreLoadNumber(1)
        mAdapter.setOnItemClickListener { adapter, view, position ->
            //跳转用户底层
            showToast(position.toString())
        }
        mAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                //跳转视频底层
                R.id.vp_item_list_follow -> {
                    val item: HomeBean.Issue.Item = adapter.getItem(position) as HomeBean.Issue.Item
                    val intent = Intent(context, VideoDetailActivity::class.java)
                    intent.putExtra(VideoDetailActivity.BUNDLE_VIDEO_DATA, item.data)
                    startActivity(intent)
                }
            }
        }
        mAdapter.setOnLoadMoreListener({
            mPresenter.requestMoreFollowData()
        }, rv_follow_list)
        rv_follow_list.adapter = mAdapter
    }

    override fun start() {
        mPresenter.requestFollowListData()
    }

    override fun setFollowListData(issue: HomeBean.Issue) {
        mAdapter.setNewData(issue.itemList)
    }

    override fun setMoreFollowListData(issue: HomeBean.Issue) {
        mAdapter.addData(issue.itemList)
        mAdapter.loadMoreComplete()
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun loadMoreFail() {
        mAdapter.loadMoreFail()
    }

    override fun loadMoreEnd() {
        mAdapter.loadMoreEnd()
    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {
        srl_follow_list.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}