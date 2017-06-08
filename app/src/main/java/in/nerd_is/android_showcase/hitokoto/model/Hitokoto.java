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

package in.nerd_is.android_showcase.hitokoto.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.sqldelight.RowMapper;

import in.nerd_is.android_showcase.common.lib_support.sqldelight.LocalDateTimeAdapter;

/**
 * @author Xuqiang ZHENG on 2017/3/5.
 */
@AutoValue
public abstract class Hitokoto implements HitokotoModel {
    // for sqldelight
    private static final LocalDateTimeAdapter DATE_TIME_ADAPTER = new LocalDateTimeAdapter();

    @SuppressWarnings("StaticInitializerReferencesSubClass")
    public static final Factory<Hitokoto> FACTORY = new Factory<>(
            AutoValue_Hitokoto::new, DATE_TIME_ADAPTER);

    public static final RowMapper<Hitokoto> SELECT_RANDOM_MAPPER = FACTORY.select_randomMapper();
    public static final RowMapper<Long> COUNT_MAPPER = FACTORY.countMapper();

    public static final JsonAdapter<Hitokoto> jsonAdapter(Moshi moshi) {
        return new AutoValue_Hitokoto.MoshiJsonAdapter(moshi);
    }
}
