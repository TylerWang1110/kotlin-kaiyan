package com.tyler.app.kotlinkaiyan.ui.activity

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.orhanobut.logger.Logger
import com.tyler.app.kotlinkaiyan.BaseApp.Companion.context
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.mvp.contract.SearchContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.SearchPresenter
import com.tyler.app.kotlinkaiyan.showToast
import com.tyler.app.kotlinkaiyan.ui.adapter.SearchResultListAdapter
import com.tyler.app.kotlinkaiyan.util.StatusBarUtils
import com.tyler.app.kotlinkaiyan.view.RecyclerViewDivider
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search_hot_tag.view.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  17:42.
 * @描述  ${搜索页}.
 */
class SearchActivity : BaseActivity(), SearchContract.View {

    private val TAG = "SearchActivity"

    private val mPresenter by lazy { SearchPresenter() }

    private val mLayoutManger: RecyclerView.LayoutManager by lazy {
        LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL, false
        )
    }

    private var mAdapter: SearchResultListAdapter? = null
    //搜索关键字
    private var mKeyWord = ""
    //当前是否显示搜索结果
    private var mIsShowResult: Boolean = false

    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    override fun initData() {

    }

    override fun initView() {
        mPresenter.attachView(this)
        //设置状态栏透明
        StatusBarUtils.darkMode(this)
        StatusBarUtils.setPaddingSmart(this, ll_search_root)
        tv_search_cancel.setOnClickListener { finish() }
        et_search.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchWord()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        et_search.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (TextUtils.isEmpty(s)) {
                    iv_search.visibility = View.VISIBLE
                    iv_search_clear.visibility = View.GONE
                } else {
                    iv_search.visibility = View.GONE
                    iv_search_clear.visibility = View.VISIBLE
                }
            }
        })
        iv_search_clear.setOnClickListener {
            et_search.setText("")
            mKeyWord = ""
            mIsShowResult = false
            switchContent()
        }
        tv_search_history_clear.setOnClickListener { mPresenter.clearSearchHistory() }

        mAdapter = SearchResultListAdapter()
        mAdapter?.setPreLoadNumber(2)
        mAdapter?.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            showToast(position.toString())
        }
        //loadMore
        mAdapter?.setOnLoadMoreListener({
            mPresenter.requestMoreSearchResultData()
        }, rv_search_result)
        rv_search_result.adapter = mAdapter
        rv_search_result.layoutManager = mLayoutManger
        rv_search_result.addItemDecoration(
                RecyclerViewDivider(
                        context,
                        LinearLayoutManager.HORIZONTAL,
                        1,
                        resources.getColor(R.color.list_dividingLine)
                )
        )
    }

    override fun start() {
        mPresenter.requestHistorySearchData()
        mPresenter.requestHotSearchData()
    }

    override fun loadMoreFail() {
        mAdapter?.loadMoreFail()
    }

    override fun loadMoreEnd() {
        mAdapter?.loadMoreEnd()
    }

    /**
     * 搜索关键字
     */
    private fun searchWord() {
        fl_search_et.requestFocus()
        //关闭软件盘
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(et_search.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        val word = et_search.text.toString().trim()
        if (word != "") {
            //清空列表
            mAdapter?.data?.clear()
            mAdapter?.notifyDataSetChanged()
            //恢复加载更多
            mAdapter?.loadMoreComplete()
            mIsShowResult = false
            switchContent()
            mKeyWord = word
            mPresenter.requestSearchResultData(word)
        }
    }

    /**
     * 设置历史搜索词
     */
    override fun setHistorySearchData(historyData: ArrayList<String>?) {
        if (historyData == null) {
            ll_search_history.visibility = View.GONE
            return
        }
        ll_search_history.visibility = View.VISIBLE
        //倒序显示
        historyData.reverse()
        tfl_search_history.adapter = object : TagAdapter<String>(historyData) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val itemView =
                        layoutInflater.inflate(R.layout.item_search_hot_tag, tfl_search_history, false) as LinearLayout
                itemView.tv_item_search_hot_tag.text = t
                return itemView
            }
        }
        tfl_search_history.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                val word = historyData[position]
                et_search.setText(word)
                et_search.setSelection(word.length)
                searchWord()
                return true
            }
        })
    }

    /**
     * 设置热门搜索词
     */
    override fun setHotSearchData(hotWords: ArrayList<String>?) {
        if (hotWords == null) {
            ll_search_hot.visibility = View.GONE
            return
        }
        ll_search_hot.visibility = View.VISIBLE
        tfl_search_hot.adapter = object : TagAdapter<String>(hotWords) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val itemView =
                        layoutInflater.inflate(R.layout.item_search_hot_tag, tfl_search_hot, false) as LinearLayout
                itemView.tv_item_search_hot_tag.text = t
                return itemView
            }
        }
        tfl_search_hot.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                val word = hotWords[position]
                et_search.setText(word)
                et_search.setSelection(word.length)
                searchWord()
                return true
            }
        })
    }

    override fun setSearchResultData(itemList: ArrayList<HomeBean.Issue.Item>, total: Int) {
        Logger.d(TAG + "${itemList.size} ----- $total")
        tv_search_result.text = "-「${mKeyWord}」搜索结果共${total}个 -"
        if (!itemList.isNullOrEmpty()) {
            mAdapter?.setNewData(itemList)
        }
        mIsShowResult = true
        switchContent()
    }

    override fun setMoreResultData(itemList: ArrayList<HomeBean.Issue.Item>) {
        if (!itemList.isNullOrEmpty()) {
            mAdapter?.addData(itemList)
            mAdapter?.loadMoreComplete()
        } else {
            loadMoreEnd()
        }
    }

    /**
     * 切换显示搜索历史 还是 搜索结果
     */
    private fun switchContent() {
        if (mIsShowResult) {
            ll_search_history.visibility = View.GONE
            ll_search_hot.visibility = View.GONE
            tv_search_result.visibility = View.VISIBLE
            rv_search_result.visibility = View.VISIBLE
        } else {
            ll_search_history.visibility = View.VISIBLE
            ll_search_hot.visibility = View.VISIBLE
            tv_search_result.visibility = View.GONE
            rv_search_result.visibility = View.GONE
        }
    }

    override fun showError(msg: String) {
        showToast(msg)
    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {

    }

    override fun onBackPressed() {
        if (mIsShowResult) {
            iv_search_clear.callOnClick()
            return
        }
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}