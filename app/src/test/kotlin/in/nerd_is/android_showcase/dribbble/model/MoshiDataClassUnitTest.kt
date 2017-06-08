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

import `in`.nerd_is.android_showcase.dribbble.moshi.DribbbleDateTimeAdapter
import com.squareup.moshi.Moshi
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 2017/5/22.
 */
class MoshiDataClassUnitTest {

    companion object {
        private val JSON = """{ "id": 73241, "name": "Leo Leung", "username": "leoleung", "html_url": "https://dribbble.com/leoleung", "avatar_url": "https://cdn.dribbble.com/users/73241/avatars/normal/e61a38bbe0e4eda3e95dc1dd19315468.png?1467911515", "bio": "Designer makes the world fresh, and programmer makes it real.", "location": "San Francisco", "links": { "web": "https://www.behance.net/supernash0303", "twitter": "https://twitter.com/lleoleung" }, "buckets_count": 27, "comments_received_count": 2442, "followers_count": 14443, "followings_count": 1797, "likes_count": 158366, "likes_received_count": 42575, "projects_count": 14, "rebounds_received_count": 59, "shots_count": 80, "teams_count": 0, "can_upload_shot": true, "type": "Player", "pro": true, "buckets_url": "https://api.dribbble.com/v1/users/73241/buckets", "followers_url": "https://api.dribbble.com/v1/users/73241/followers", "following_url": "https://api.dribbble.com/v1/users/73241/following", "likes_url": "https://api.dribbble.com/v1/users/73241/likes", "projects_url": "https://api.dribbble.com/v1/users/73241/projects", "shots_url": "https://api.dribbble.com/v1/users/73241/shots", "teams_url": "https://api.dribbble.com/v1/users/73241/teams", "created_at": "2011-11-04T13:43:40Z", "updated_at": "2017-05-22T07:36:53Z" }"""
        private val adapter = Moshi.Builder()
                .add(DribbbleDateTimeAdapter())
                .build()
                .adapter(User::class.java)
    }

    @Test
    fun convertJsonToDataClass_passed() {
        val user = adapter.fromJson(JSON)

        assertThat("result is not null", user, notNullValue())
    }
}