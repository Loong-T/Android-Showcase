/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package `in`.nerd_is.android_showcase.zhihu_daily.model

import com.squareup.moshi.Json

/**
 * @author Xuqiang ZHENG on 2017/6/13.
 */
data class StoryDetail(val id: Long,
                       val type: Int,
                       val body: String,
                       @Json(name = "image_source") val imageSource: String,
                       val title: String,
                       val image: String,
                       @Json(name = "share_url") val shareUrl: String,
                       val js: List<String>,
                       val recommenders: List<Recommender>?,
                       val section: Section?,
                       val css: List<String>) {

    fun toHtml(nightMode: Boolean): String {
        val builder = StringBuilder()
        builder.append("<!DOCTYPE>")
                .append("<html>")
                .append("<head>")
                .append("<title>").append(title).append("</title>")
                .append("<meta charset=\"UTF-8\"/>")
                .append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>")

        for (css in css) {
            builder.append("<link rel=\"stylesheet\" href=\"")
                    .append(css)
                    .append("\"/>")
        }
        // hide headline
        builder.append("<style>")
                .append(".headline { display: none }")
                .append("</style>")

        for (js in js) {
            builder.append("<script src=\"")
                    .append(js)
                    .append("\"/>")
        }

        builder.append("</head>")

        builder.append(if (nightMode) "<body class=\"night\">" else "<body>")
                .append(body)
                .append("</body>")

        // add click listener for images
        builder.append("<script type=\"text/javascript\">")
                .append("    var imgs = document.getElementsByClassName('content-image');")
                .append("    for (var i = 0; i< imgs.length; ++i) {")
                .append("        imgs[i].addEventListener('click', function (e) {")
                .append("            activity.startImageViewer(e.srcElement.src);")
                .append("        });")
                .append("    }")
                .append("</script>")

        builder.append("</html>")

        return builder.toString()
    }
}

data class Recommender(val avatar: String?)

data class Section(val id: Long,
                   val name: String,
                   val thumbnail: String?)