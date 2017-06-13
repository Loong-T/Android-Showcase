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

package `in`.nerd_is.android_showcase.common.lib_support.room

import `in`.nerd_is.android_showcase.utils.DateUtils
import android.arch.persistence.room.TypeConverter
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime

/**
 * @author Xuqiang ZHENG on 2017/6/10.
 */
class Converters {

    companion object {
        private const val SEPARATOR = ","
    }

    @TypeConverter
    fun fromMillisToLocalDateTime(millis: Long) = DateUtils.fromMillis(millis)

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime) = DateUtils.toMillis(dateTime)

    @TypeConverter
    fun fromMillisToLocalDate(millis: Long) = DateUtils.fromMillis(millis).toLocalDate()

    @TypeConverter
    fun fromLocalDate(date: LocalDate) = DateUtils.toMillis(date)

    @TypeConverter
    fun fromStringListToString(list: List<String>) = list.joinToString(SEPARATOR)

    @TypeConverter
    fun fromStringToStringList(str: String) = str.split(SEPARATOR)
}