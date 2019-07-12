package com.tyler.app.kotlinkaiyan.ui.adapter

import android.annotation.SuppressLint
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.dp2px
import com.tyler.app.kotlinkaiyan.mvp.model.bean.HomeBean
import com.tyler.app.kotlinkaiyan.util.DateUtils
import kotlinx.android.synthetic.main.vp_item_follow.view.*

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/10  17:13.
 * @描述  ${关注列表-item-pager数据}.
 */
class FollowPagerAdapter(var itemList: ArrayList<HomeBean.Issue.Item>?) : PagerAdapter() {

    private var mOnPageClickListener: OnPageClickListener? = null

    open interface OnPageClickListener {
        fun onPageClick(position: Int)
    }

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View =
            LayoutInflater.from(container.context).inflate(R.layout.vp_item_follow, container, false).apply {
                if (count != 0) {
                    val item = itemList!!.get(position)
                    Glide.with(context)
                        .load(item.data.cover.feed)
                        .placeholder(R.drawable.shape_base_img_gray_dp5)
                        .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(context.dp2px(5f))))
                        .error(R.drawable.shape_base_img_gray_dp5)
                        .into(iv_vp_item_follow_img)

                    tv_vp_item_follow_duration.text = DateUtils.getVideoDuration(item.data.duration)
                    tv_vp_item_follow_title.text = item.data.title
                    var tag = ""
                    item.data.tags.forEach { tag += "${it.name} / " }
                    if (tag.endsWith(" / ")) {
                        tag = tag.substring(0, tag.length - 3)
                    }
                    tv_vp_item_follow_tags.text = "#${item.data.category}    $tag"
                }
            }
        itemView.setOnClickListener {
            mOnPageClickListener?.onPageClick(position)
        }
        container.addView(itemView)
        return itemView
    }

    override fun isViewFromObject(p0: View, p1: Any): Boolean {
        return p0 == p1
    }

    override fun getCount(): Int {
        if (itemList.isNullOrEmpty()) {
            return 0
        } else {
            return itemList!!.size
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    fun setOnPageClickListener(onPageClickListener: OnPageClickListener) {
        mOnPageClickListener = onPageClickListener
    }
}