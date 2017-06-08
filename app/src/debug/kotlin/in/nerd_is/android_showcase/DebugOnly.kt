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

package `in`.nerd_is.android_showcase

import android.content.Context

import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author Xuqiang ZHENG on 2017/3/14.
 */
object DebugOnly {
    @JvmStatic fun initStetho(context: Context) {
        Stetho.initializeWithDefaults(context)
    }

    fun OkHttpClient.Builder.addStethoInterceptor(): OkHttpClient.Builder {
        return this.addNetworkInterceptor(StethoInterceptor())
    }

    fun OkHttpClient.Builder.addLoggingInterceptor(): OkHttpClient.Builder {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        return this.addInterceptor(interceptor)
    }
}
