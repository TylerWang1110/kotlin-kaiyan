package com.tyler.app.kotlinkaiyan.mvp.model.bean

/**
 * @创建者  Tyler Wang.
 * @创建时间  2019/7/5  15:59.
 * @描述  ${首页精选}.
 */
data class HomeBean(
    val issueList: ArrayList<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
) {
    data class Issue(
        var count: Int,
        var total: Int,
        var date: Long,
        var publishTime: Long,
        var releaseTime: Long,
        var type: String,
        var nextPageUrl: String?,
        var itemList: ArrayList<Item>
    ) {
        data class Item(
            var adIndex: Int,
            var id: Int,
            //video textHeader banner2等...只处理video类型
            var type: String,
            var data: Data
        ) {
            data class Data(
                var ad: Boolean,
                var author: Author?,
                var category: String,
                var dataType: String,
                var cover: Cover,
                var date: Long,
                var description: String,
                var descriptionEditor: String,
                var descriptionPgc: String,
                var duration: Int,
                var header: Header,
                var id: Int,
                var itemList: ArrayList<HomeBean.Issue.Item>,
                var playInfo: ArrayList<PlayInfo>,
                var playUrl: String,
                var played: Boolean,
                var releaseTime: Long,
                var remark: String,
                var slogan: String,
                var title: String,
                var titlePgc: String,
                var type: String,
                var tags: ArrayList<TagData>,
                var webUrl: WebUrl
            ) {
                data class Author(
                    var description: String,
                    var icon: String,
                    var name: String,
                    var ifPgc: Boolean
                )

                data class Cover(
                    var blurred: String,
                    var detail: String,
                    var feed: String,
                    var homepage: String
                )

                data class Header(
                    var description: String,
                    var icon: String,
                    var title: String,
                    var id: Int,
                    var ifPgc: Boolean
                )

                data class PlayInfo(
                    var height: Int,
                    var name: String,
                    var type: String,
                    var url: String,
                    var urlList: ArrayList<UrlData>
                ) {
                    data class UrlData(
                        var name: String,
                        var size: Long,
                        var url: String
                    )
                }

                data class TagData(
                    var actionUrl: String,
                    var bgPicture: String,
                    var desc: String,
                    var headerImage: String,
                    var name: String
                )

                data class WebUrl(
                    var forWeibo: String,
                    var raw: String
                )
            }
        }
    }
}