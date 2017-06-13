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

import `in`.nerd_is.android_showcase.dribbble.model.MoshiDataClassTest
import `in`.nerd_is.android_showcase.main.MainPresenterTest
import `in`.nerd_is.android_showcase.zhihu_daily.MoshiDateTimeAdapterTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * @author Xuqiang ZHENG on 2016/10/22.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
        MainPresenterTest::class,
        MoshiDateTimeAdapterTest::class,
        MoshiDataClassTest::class
)
class UnitTestSuite
