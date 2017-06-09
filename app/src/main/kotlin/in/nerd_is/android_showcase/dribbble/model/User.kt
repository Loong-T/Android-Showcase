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
data class User(val id: Long,
                val name: String,
                val username: String,
                @Json(name = "html_url") val htmlUrl: String,
                @Json(name = "avatar_url") val avatarUrl: String,
                val bio: String,
                val location: String,
                val links: Links,
                @Json(name = "buckets_count") val bucketCount: Int,
                @Json(name = "comments_received_count") val commentsReceivedCount: Int,
                @Json(name = "followers_count") val followersCount: Int,
                @Json(name = "followings_count") val followingsCount: Int,
                @Json(name = "likes_count") val likesCount: Int,
                @Json(name = "likes_received_count") val likesReceivedCount: Int,
                @Json(name = "projects_count") val projectsCount: Int,
                @Json(name = "rebounds_received_count") val reboundsReceivedCount: Int,
                @Json(name = "shots_count") val shotsCount: Int,
                @Json(name = "teams_count") val teamsCount: Int,
                @Json(name = "can_upload_shot") val canUploadShot: Boolean,
                val type: String,
                val pro: Boolean,
                @Json(name = "buckets_url") val bucketsUrl: String,
                @Json(name = "followers_url") val followersUrl: String,
                @Json(name = "following_url") val followingUrl: String,
                @Json(name = "likes_url") val likesUrl: String,
                @Json(name = "projects_url") val projectsUrl: String,
                @Json(name = "shots_url") val shotsUrl: String,
                @Json(name = "teams_url") val teamsUrl: String?,
                @Json(name = "created_at") val createdAt: LocalDateTime,
                @Json(name = "updated_at") val updatedAt: LocalDateTime,
                @Json(name = "members_count") val membersCount: Int?,
                @Json(name = "members_url") val membersUrl: String?,
                @Json(name = "team_shots_url") val teamShotsUrl: String?)

