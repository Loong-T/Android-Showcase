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

package `in`.nerd_is.android_showcase.main

import `in`.nerd_is.android_showcase.dribbble.DribbbleModule
import `in`.nerd_is.android_showcase.dribbble_shots.DribbbleShotsFragment
import `in`.nerd_is.android_showcase.zhihu_daily.ZhihuDailyModule
import `in`.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Xuqiang ZHENG on 2017/6/27.
 */
@Module
abstract class MainFragmentBuilderModule {
    @ContributesAndroidInjector(modules = arrayOf(ZhihuDailyModule::class))
    abstract fun contributeZhihuDailyListFragment(): ZhihuDailyListFragment

    @ContributesAndroidInjector(modules = arrayOf(DribbbleModule::class))
    abstract fun contributeDribbbleShotsFragment(): DribbbleShotsFragment
}