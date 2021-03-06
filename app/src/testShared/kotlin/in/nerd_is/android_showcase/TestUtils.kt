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

import `in`.nerd_is.android_showcase.hitokoto.model.Hitokoto
import `in`.nerd_is.android_showcase.utils.DateUtils
import com.github.javafaker.Faker
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * @author Xuqiang ZHENG on 2017/6/11.
 */
object TestUtils {
    @JvmStatic fun generateHitokoto(): Hitokoto {
        val faker = Faker(Locale.getDefault())
        return Hitokoto(
                faker.idNumber().valid(),
                faker.idNumber().ssnValid(),
                faker.hacker().noun(),
                faker.lorem().sentence(),
                faker.name().name(),
                faker.book().title(),
                DateUtils.fromDate(faker.date().past(42, TimeUnit.DAYS))
        )
    }
}