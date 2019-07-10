package com.tyler.app.kotlinkaiyan.mvp.presenter

import com.tyler.app.kotlinkaiyan.base.BasePresenter
import com.tyler.app.kotlinkaiyan.mvp.contract.MainHomeContract
import com.tyler.app.kotlinkaiyan.mvp.model.MainHomeModel
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.net.ExceptionHandler

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  11:16.
 * @描述  ${首页Presenter}.
 */
class MainHomePresenter : BasePresenter<MainHomeContract.View>(), MainHomeContract.Presenter {

    private var mBannerHomeBean: HomeBean? = null
    private var mNextPageUrl = ""
    private val mHomeModel: MainHomeModel by lazy { MainHomeModel() }

    /**
     * 第一页数据
     */
    override fun requestFirstData(num: Int) {
        checkViewAttach()
        val disposable = mHomeModel.requestDailyFirstData(num)
                .flatMap { homeBean ->
                    val itemList = homeBean.issueList[0].itemList
                    itemList.filter { item -> item.type == "banner2" || item.type == "horizontalScrollCard" }
                            .forEach { item -> itemList.remove(item) }
                    mBannerHomeBean = homeBean
                    mNextPageUrl = homeBean.nextPageUrl
                    mHomeModel.requestDailyNextData(homeBean.nextPageUrl)
                }
                .subscribe({ homeBean ->
                    mView?.apply {
                        dismissLoadding()
                        mNextPageUrl = homeBean.nextPageUrl
                        val itemList = homeBean.issueList[0].itemList
                        itemList.filter { item -> item.type != "video" }
                                .forEach { item -> itemList.remove(item) }
                        setFirstData(homeBean)
                    }
                }, {
                    mView?.apply {
                        dismissLoadding()
                        showError(ExceptionHandler.handException(it))
                    }
                })

        addSubscription(disposable)
    }

    override fun requestNextData() {
        checkViewAttach()
        val disposable = mNextPageUrl.let {
            mHomeModel.requestDailyNextData(it)
                    .subscribe({ homeBeam ->
                        mView?.apply {
                            val itemList = homeBeam.issueList[0].itemList
                            itemList.filter { item -> item.type != "video" }
                                    .forEach { item -> itemList.remove(item) }
                            mNextPageUrl = homeBeam.nextPageUrl
                            setNextData(itemList)
                        }
                    }, {
                        mView?.apply {
                            dismissLoadding()
                            loadMoreFail()
                            showError(ExceptionHandler.handException(it))
                        }
                    })
        }

        addSubscription(disposable)
    }
}