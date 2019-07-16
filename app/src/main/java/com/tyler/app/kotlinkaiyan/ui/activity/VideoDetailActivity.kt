package com.tyler.app.kotlinkaiyan.ui.activity

import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.base.BaseActivity
import com.tyler.app.kotlinkaiyan.mvp.contract.VideoDetailContract
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.mvp.presenter.VideoDetailPresenter

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/16  16:54.
 * @描述  ${视频详情]}.
 */
class VideoDetailActivity : BaseActivity(), VideoDetailContract.View {

    companion object {
        const val BUNDLE_VIDEO_DATA = "bundle_video_data"
    }

    private var mVideoData: HomeBean.Issue.Item.Data? = null

    private val mPresenter by lazy { VideoDetailPresenter() }

    override fun layoutId(): Int {
        return R.layout.activity_video_detail
    }

    override fun initData() {
        mVideoData = intent.getParcelableExtra(BUNDLE_VIDEO_DATA)
    }

    override fun initView() {

    }

    override fun start() {

    }

    override fun setRelatedVideoData(itemList: ArrayList<HomeBean.Issue.Item>?) {

    }

    override fun playVideo() {

    }

    override fun showLoadding() {

    }

    override fun dismissLoadding() {

    }

    override fun showError(msg: String) {

    }
}