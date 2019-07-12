package com.tyler.app.kotlinkaiyan.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseFragment
import com.tyler.app.kotlinkaiyan.mvp.contract.RankListContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.RankListPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.adapter.SearchResultListAdapter
import com.tyler.app.kotlinkaiyan.view.RecyclerViewDivider
import kotlinx.android.synthetic.main.fragment_rank_list.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  11:29.
 * @描述  ${排行榜}.
 */
class RankListFragment : BaseFragment(), RankListContract.View {

    private var mType: String? = null
    private val mPresenter by lazy { RankListPresenter() }
    private val mAdapter by lazy { SearchResultListAdapter() }
    private val mLayoutManager by lazy { LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) }

    companion object {
        const val BUNDLE_RANK_TYPE = "rank_type"
        const val RANK_TYPE_WEEKLY = "weekly"
        const val RANK_TYPE_MONTHLY = "monthly"
        const val RANK_TYPE_HISTORICAL = "historical"

        fun getInstance(type: String): RankListFragment {
            val fragment = RankListFragment()
            val bundle = Bundle()
            bundle.putString(BUNDLE_RANK_TYPE, type)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun layoutId(): Int {
        return R.layout.fragment_rank_list
    }

    override fun initData() {
        mType = arguments?.getString(BUNDLE_RANK_TYPE)
    }

    override fun initView() {
        mPresenter.attachView(this)
        srl_rank_list.setOnRefreshListener {
            start()
        }
        mh_rank_list.setColorSchemeResources(R.color.text_color_black)
        rv_rank_list.layoutManager = mLayoutManager
        mAdapter.setOnItemClickListener { adapter, view, position ->

        }
        mAdapter.setEnableLoadMore(false)
        rv_rank_list.adapter = mAdapter
        rv_rank_list.addItemDecoration(
                RecyclerViewDivider(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        1,
                        resources.getColor(R.color.list_dividingLine)
                )
        )
    }

    override fun start() {
        mType?.let { mPresenter.requestRankListData(it) }
    }

    override fun setRankListData(itemList: ArrayList<HomeBean.Issue.Item>) {
        mAdapter.setNewData(itemList)
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {
        srl_rank_list.finishRefresh()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}