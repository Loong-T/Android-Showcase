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

package `in`.nerd_is.android_showcase.hitokoto.dao

import `in`.nerd_is.android_showcase.AppDatabase
import `in`.nerd_is.android_showcase.TestUtils
import `in`.nerd_is.android_showcase.hitokoto.model.Hitokoto
import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @author Xuqiang ZHENG on 2017/6/11.
 */
@RunWith(AndroidJUnit4::class)
class HitokotoEntityTest {
    private lateinit var db: AppDatabase
    private lateinit var dao: HitokotoDao

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.hitokotoDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun insertAndQueryAll_queryResultInCollection() {
        val hitokotoList = ArrayList<Hitokoto>(5)
        (1..5).fold(hitokotoList, { list, _ ->
            val hitokoto = TestUtils.generateHitokoto()
            if (hitokoto !in list) list.add(hitokoto)
            list
        })

        try {
            db.beginTransaction()
            hitokotoList.forEach { dao.insertHitokoto(it) }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }

        val testObserver = dao.countHitokoto().test()
        testObserver.assertSubscribed()
                .awaitCount(1)
                .assertNoErrors()
                .assertValue(hitokotoList.size.toLong())

        val testObserver1 = dao.selectAll().test()
        testObserver1.assertSubscribed()
                .awaitCount(1)
                .assertNoErrors()
                .assertValue { hitokotoList.containsAll(it) }
    }

    @Test
    fun insertAndQueryById_queryResultEquals() {
        val hitokoto = TestUtils.generateHitokoto()
        val id = hitokoto.id

        val rowId = dao.insertHitokoto(hitokoto)
        assertThat("insert succeed", rowId, not(-1L))

        val testObserver = dao.selectById(id).test()
        testObserver.awaitCount(1)
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(hitokoto)
    }

    @Test
    fun insertToReplace_dataUpdated() {
        val hitokoto = TestUtils.generateHitokoto()

        dao.insertHitokoto(hitokoto)

        val changedText = "text after change"
        val changedValue = hitokoto.copy(text = changedText)
        dao.insertHitokoto(changedValue)

        val testObserver = dao.selectById(hitokoto.id).test()
        testObserver.awaitCount(1)
                .assertSubscribed()
                .assertNoErrors()
                .assertValue(changedValue)
        val result = testObserver.values()[0]
        assertThat("query succeed", result, notNullValue())
        assertThat("text changed", result.text, equalTo(changedText))
    }
}