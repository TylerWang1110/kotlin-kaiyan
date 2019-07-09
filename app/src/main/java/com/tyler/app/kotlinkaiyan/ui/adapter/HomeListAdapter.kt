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
import com.tyler.app.kotlinkaiyan.util.DateUtil
import java.text.SimpleDateFormat
import java.util.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/8  11:27.
 * @描述  ${首页列表}.
 */
class HomeListAdapter :
        BaseQuickAdapter<HomeBean.Issue.Item, BaseViewHolder>(R.layout.item_list_home, ArrayList<HomeBean.Issue.Item>()) {

    var mTypeface: Typeface? = null
    var mTypefaceTitle: Typeface? = null

    init {
        mTypeface = Typeface.createFromAsset(BaseApp.context.assets, "fonts/FZLanTingHeiS-L-GB-Regular.TTF")
        mTypefaceTitle = Typeface.createFromAsset(BaseApp.context.assets, "fonts/Lobster-1.4.otf")
    }

    override fun convert(helper: BaseViewHolder?, item: HomeBean.Issue.Item?) {
        val position: Int = helper?.adapterPosition ?: 0
        val showTitle: Boolean
        if (position == 0) {
            showTitle = true
        } else {
            val lastItem = data.get(position - 1)
            showTitle = lastItem.data.date != item?.data?.date
        }
        helper!!.setText(
                R.id.tv_item_list_home_title,
                DateUtil.formatDate(item?.data?.date!!, SimpleDateFormat("- MMM. dd -", Locale.ENGLISH))
        )
                .setGone(R.id.tv_item_list_home_title, showTitle)
                .setTypeface(R.id.tv_item_list_home_title, mTypefaceTitle)
                .setText(R.id.tv_item_list_home_duration, DateUtil.getVideoDuration(item.data.duration))
                .setText(R.id.tv_item_list_home_video_title, item.data.title)
                .setText(R.id.tv_item_list_home_author_name, item.data.author.name + "  /  #" + item.data.category)
                .setTypeface(R.id.tv_item_list_home_author_name, mTypeface)


        val ivAvatar: ImageView = helper.getView(R.id.iv_item_list_home_author_avatar)
        val ivImg: ImageView = helper.getView(R.id.iv_item_list_home)
        Glide.with(mContext)
                .load(item.data.author.icon)
                .placeholder(R.drawable.shape_home_list_avatar)
                .error(R.drawable.shape_home_list_avatar)
                .transform(RoundedCorners(mContext.dp2px(20f)))
                .into(ivAvatar)
        Glide.with(mContext)
                .load(item.data.cover.feed)
                .placeholder(R.drawable.shape_home_list_img)
                .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(mContext.dp2px(5f))))
                .error(R.drawable.shape_home_list_img)
                .into(ivImg)
    }

}