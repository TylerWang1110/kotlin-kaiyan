package com.tyler.app.kotlinkaiyan.ui.adapter

import android.widget.ImageView
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.tyler.app.kotlinkaiyan.BaseApp.Companion.context
import com.tyler.app.kotlinkaiyan.GlideApp
import com.tyler.app.kotlinkaiyan.R
import com.tyler.app.kotlinkaiyan.dp2px
import com.tyler.app.kotlinkaiyan.mvp.model.bean.CategoryBean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/12  9:27.
 * @描述  ${分类}.
 */
class CategoryListAdapter :
        BaseQuickAdapter<CategoryBean, BaseViewHolder>(R.layout.item_list_category, ArrayList<CategoryBean>()) {

    override fun convert(helper: BaseViewHolder?, item: CategoryBean?) {
        helper?.setText(R.id.tv_list_category, "#${item?.name}")
        val iv: ImageView? = helper?.getView(R.id.iv_list_category)
        if (iv != null) {
            GlideApp.with(mContext)
                    .load(item?.bgPicture)
                    .placeholder(R.drawable.shape_base_img_black_dp5)
                    .error(R.drawable.shape_base_img_black_dp5)
                    .apply(RequestOptions().transforms(CenterCrop(), RoundedCorners(context.dp2px(5f))))
                    .into(iv)
        }
    }

}