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

package `in`.nerd_is.android_showcase.dribbble.model.repository.remote

import `in`.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitModule
import `in`.nerd_is.android_showcase.dribbble.model.Shot
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsEmptyCollection.empty
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 2017/6/7.
 */
class DribbbleRemoteRepositoryTest {

    @Test
    fun getShots_notNullValue() {
        val repo = DribbbleRemoteRepository(
                RetrofitModule().provideDribbbleRetrofit(),
                Schedulers.io(),
                Schedulers.computation())

        val testObserver: TestObserver<List<Shot>> = repo.getShots().test()

        testObserver.assertSubscribed()
                .assertComplete()
                .assertNoErrors()

        assertThat("has one result", testObserver.valueCount(), equalTo(1))
        val list = testObserver.values()[0]
        assertThat("list is not null", list, notNullValue())
        assertThat("list is not empty", list, not(empty()))
    }
}