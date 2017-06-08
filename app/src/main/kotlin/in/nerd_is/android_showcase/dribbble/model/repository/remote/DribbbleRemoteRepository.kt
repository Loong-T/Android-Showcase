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

package `in`.nerd_is.android_showcase.dribbble.model.repository.remote

import `in`.nerd_is.android_showcase.BuildConfig
import `in`.nerd_is.android_showcase.common.Constant.TAG_DRIBBBLE
import `in`.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitUtils
import `in`.nerd_is.android_showcase.dribbble.ParamName
import `in`.nerd_is.android_showcase.dribbble.model.Shot
import `in`.nerd_is.android_showcase.dribbble.model.repository.DribbbleDataSource
import `in`.nerd_is.android_showcase.dribbble.model.repository.remote.DribbbleUrl.SHOTS
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.QueryMap
import javax.inject.Named

/**
 * @author Xuqiang ZHENG on 2017/6/5.
 */
class DribbbleRemoteRepository(@Named(TAG_DRIBBBLE) retrofit: Retrofit) : DribbbleDataSource {

    private val api = RetrofitUtils.create(retrofit, Api::class.java)

    override fun getShots(): Single<List<Shot>> {
        return api.getShots(mapOf(ParamName.ACCESS_TOKEN to BuildConfig.DRIBBBLE_ACCESS_TOKEN))
    }

    private interface Api {
        @GET(value = SHOTS)
        fun getShots(@QueryMap params: Map<String, String>): Single<List<Shot>>
    }
}