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

import `in`.nerd_is.android_showcase.common.lib_support.room.Converters
import `in`.nerd_is.android_showcase.hitokoto.dao.HitokotoDao
import `in`.nerd_is.android_showcase.hitokoto.model.Hitokoto
import `in`.nerd_is.android_showcase.zhihu_daily.dao.ZhihuDailyDao
import `in`.nerd_is.android_showcase.zhihu_daily.model.Story
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters

/**
 * @author Xuqiang ZHENG on 2017/6/10.
 */
@Database(version = 1, entities = arrayOf(
        Hitokoto::class,
        Story::class
))
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun hitokotoDao(): HitokotoDao

    abstract fun zhihuDailyDao(): ZhihuDailyDao
}