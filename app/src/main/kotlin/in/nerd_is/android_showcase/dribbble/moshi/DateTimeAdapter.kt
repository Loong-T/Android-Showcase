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

package `in`.nerd_is.android_showcase.dribbble.moshi

import `in`.nerd_is.android_showcase.dribbble.Constant.DATE_TIME_FORMATTER
import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.temporal.ChronoField

/**
 * @author Xuqiang ZHENG on 2017/5/22.
 */
class DateTimeAdapter {
    @ToJson fun toJson(date: LocalDateTime) = date.format(DATE_TIME_FORMATTER)

    @FromJson fun fromJson(json: String) = DATE_TIME_FORMATTER.parse(json).query {
        LocalDateTime.ofEpochSecond(it.getLong(ChronoField.INSTANT_SECONDS), 0, ZoneOffset.UTC)
    }
}