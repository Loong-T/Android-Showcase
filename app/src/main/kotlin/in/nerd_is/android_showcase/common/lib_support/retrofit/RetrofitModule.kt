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

package `in`.nerd_is.android_showcase.common.lib_support.retrofit

import `in`.nerd_is.android_showcase.DebugOnly.addLoggingInterceptor
import `in`.nerd_is.android_showcase.DebugOnly.addStethoInterceptor
import `in`.nerd_is.android_showcase.common.Constant.*
import `in`.nerd_is.android_showcase.dribbble.model.repository.remote.DribbbleUrl
import `in`.nerd_is.android_showcase.dribbble.moshi.DribbbleDateTimeAdapter
import `in`.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoUrl
import `in`.nerd_is.android_showcase.hitokoto.moshi.HitokotoDateTimeAdapter
import `in`.nerd_is.android_showcase.zhihu_daily.model.repository.remote.ZhihuDailyUrl
import `in`.nerd_is.android_showcase.zhihu_daily.moshi.ZhihuDailyDateAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

/**
 * @author Xuqiang ZHENG on 2016/11/25.
 */
@Module
class RetrofitModule {

    private val okHttpClient = OkHttpClient.Builder()
            .addLoggingInterceptor()
            .addStethoInterceptor()
            .build()
    private val rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Singleton
    @Provides
    @Named(TAG_HITOKOTO)
    fun provideHitokotoRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(HitokotoDateTimeAdapter()).build()
        return createRetrofit(moshi, HitokotoUrl.BASE_URL)
    }

    @Singleton
    @Provides
    @Named(TAG_ZHIHU_DAILY)
    fun provideZhihuDailyRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(ZhihuDailyDateAdapter()).build()
        return createRetrofit(moshi, ZhihuDailyUrl.BASE_URL)
    }

    @Singleton
    @Provides
    @Named(TAG_DRIBBBLE)
    fun provideDribbbleRetrofit(): Retrofit {
        val moshi = Moshi.Builder().add(DribbbleDateTimeAdapter()).build()
        return createRetrofit(moshi, DribbbleUrl.BASE_URL)
    }

    private fun createRetrofit(moshi: Moshi, baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .build()
    }
}
