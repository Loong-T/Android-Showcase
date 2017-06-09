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

package `in`.nerd_is.android_showcase.dribbble.model

import com.squareup.moshi.Json
import org.threeten.bp.LocalDateTime

/**
 * @author Xuqiang ZHENG on 2017/5/22.
 */
data class Shot(val id: Long,
                val title: String,
                val description: String,
                val width: Int,
                val height: Int,
                val images: Image,
                @Json(name = "views_count") val viewsCount: Int,
                @Json(name = "likes_count") val likesCount: Int,
                @Json(name = "comments_count") val commentsCount: Int,
                @Json(name = "attachments_count") val attachmentsCount: Int,
                @Json(name = "rebounds_count") val reboundsCount: Int,
                @Json(name = "buckets_count") val bucketsCount: Int,
                @Json(name = "created_at") val createdAt: LocalDateTime,
                @Json(name = "updated_at") val updatedAt: LocalDateTime,
                @Json(name = "html_url") val htmlUrl: String,
                @Json(name = "attachments_url") val attachmentsUrl: String,
                @Json(name = "buckets_url") val bucketsUrl: String,
                @Json(name = "comments_url") val commentsUrl: String,
                @Json(name = "likes_url") val likesUrl: String,
                @Json(name = "projects_url") val projectsUrl: String,
                @Json(name = "rebounds_url") val reboundsUrl: String,
                @Json(name = "rebound_source_url") val reboundSourceUrl: String,
                val animated: Boolean,
                val tags: List<String>,
                val user: User,
                val team: User)