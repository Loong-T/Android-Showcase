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

import retrofit2.Retrofit

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
object RetrofitUtils {
    @JvmStatic fun <T> create(retrofit: Retrofit, clazz: Class<T>): T {
        return retrofit.create(clazz)
    }
}
