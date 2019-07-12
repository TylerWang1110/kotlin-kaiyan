package com.tyler.app.kotlinkaiyan.mvp.model.bean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/11  16:45.
 * @描述  ${分类}.
 */
data class CategoryBean(
    var bgPicture: String,
    var defaultAuthorId: Int,
    var description: String,
    var headerImage: String,
    var id: Int,
    var name: String,
    var tagId: Int
)