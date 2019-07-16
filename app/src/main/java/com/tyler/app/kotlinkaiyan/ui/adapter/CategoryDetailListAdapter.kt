package com.tyler.app.kotlinkaiyan.ui.adapter

import android.graphics.Typeface
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tyler.app.kotlinkaiyan.BaseApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.dp2px
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.util.DateUtils
import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  11:27.
 * @描述  ${搜索结果列表}.
 */
class CategoryDetailListAdapter :
        BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_list_category_detail, ArrayList<HomeBean.Issue.Item>()) {

    var mTypeface: Typeface? = null

    init {
        mTypeface = Typeface.createFromAsset(BaseApp.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
    }

    override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        helper!!.setText(
                R.id.tv_item_list_home_title,
                DateUtils.formatDate(item?.data?.date!!, SimpleDateFormat("- MMM. dd -", Locale.ENGLISH))
        )
                .setGone(R.id.tv_item_list_home_title, false)
                .setText(R.id.tv_item_list_home_duration, DateUtils.getVideoDuration(item.data.duration))
                .setText(R.id.tv_item_list_home_video_title, item.data.title)
                .setText(
                        R.id.tv_item_list_home_author_name,
                        item.data.author?.let { it.name + "  /  #" + item.data.category }
                                ?: { "#" + item.data.category }.toString()
                )
                .setTypeface(R.id.tv_item_list_home_author_name, mTypeface)

        if (item.data.author == null) {
            helper.setGone(R.id.iv_item_list_home_author_avatar_tag, false)
        } else {
            helper.setGone(R.id.iv_item_list_home_author_avatar_tag, item.data.author!!.ifPgc)
        }

        val ivAvatar: ImageView = helper.getView(R.id.iv_item_list_home_author_avatar)
        val ivImg: ImageView = helper.getView(R.id.iv_item_list_home)
        Glide.with(mContext)
                .load(item.data.author?.icon)
                .placeholder(R.drawable.shape_home_list_avatar)
                .error(R.drawable.shape_home_list_avatar)
                .transform(RoundedCorners(mContext.dp2px(20f)))
                .into(ivAvatar)
        Glide.with(mContext)
                .load(item.data.cover.feed)
                .placeholder(R.drawable.shape_base_img_gray_dp5)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(mContext.dp2px(5f))))
                .error(R.drawable.shape_base_img_gray_dp5)
                .into(ivImg)
    }

}