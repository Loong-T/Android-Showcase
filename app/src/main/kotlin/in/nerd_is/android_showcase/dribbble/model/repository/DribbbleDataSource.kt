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

package `in`.nerd_is.android_showcase.dribbble.model.repository

import `in`.nerd_is.android_showcase.dribbble.model.Shot
import io.reactivex.Single

/**
 * @author Xuqiang ZHENG on 2017/6/5.
 */
interface DribbbleDataSource {
    fun getShots(): Single<List<Shot>>
    fun getNextPageShots(page: Int): Single<List<Shot>>
}