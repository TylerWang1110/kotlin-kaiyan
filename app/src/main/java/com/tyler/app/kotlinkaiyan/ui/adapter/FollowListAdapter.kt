package com.tyler.app.kotlinkaiyan.ui.adapter

import android.support.v4.view.ViewPager
import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tyler.app.kotlinkaiyan.GlideApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.dp2px
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  15:59.
 * @描述  ${关注列表}.
 */
class FollowListAdapter :
    BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_list_follow, ArrayList<HomeBean.Issue.Item>()) {

    override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        helper?.setText(R.id.tv_author_name, item?.data?.header?.title)
            ?.setText(R.id.tv_author_description, item?.data?.header?.description)
            ?.addOnClickListener(R.id.vp_item_list_follow)

        val ivAvatar: ImageView? = helper?.getView(R.id.iv_author_avatar)
        if (ivAvatar != null) {
            GlideApp.with(mContext)
                .load(item?.data?.header?.icon)
                .placeholder(R.drawable.shape_home_list_avatar)
                .error(R.drawable.shape_home_list_avatar)
                .transform(RoundedCorners(mContext.dp2px(20f)))
                .into(ivAvatar)
        }
        if (item?.data?.header == null) {
            helper?.setGone(R.id.iv_author_avatar_tag, false)
        } else {
            helper?.setGone(R.id.iv_author_avatar_tag, item.data.header.ifPgc)
        }

        val vp: ViewPager? = helper?.getView(R.id.vp_item_list_follow)
        if (vp != null) {
            val pagerAdapter = FollowPagerAdapter(item?.data?.itemList)
            pagerAdapter.setOnPageClickListener(object : FollowPagerAdapter.OnPageClickListener {
                override fun onPageClick(position: Int) {
                    vp.callOnClick()
                }
            })
            vp.adapter = pagerAdapter
            vp.currentItem = 0
        }
    }

}