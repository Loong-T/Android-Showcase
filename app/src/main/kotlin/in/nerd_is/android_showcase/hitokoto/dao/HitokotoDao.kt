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

import `in`.nerd_is.android_showcase.hitokoto.model.Hitokoto
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * @author Xuqiang ZHENG on 2017/6/10.
 */
@Dao
interface HitokotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHitokoto(hitokoto: Hitokoto): Long

    @Query("SELECT * FROM hitokoto ORDER BY RANDOM() LIMIT 1")
    fun selectRandomHitokoto(): Flowable<Hitokoto>

    @Query("SELECT * FROM hitokoto WHERE id = :p0")
    // TODO: a kapt bug here, need to change :p0 to :id after fixed
    fun selectById(id: String): Flowable<Hitokoto>

    @Query("SELECT * FROM hitokoto")
    fun selectAll(): Flowable<List<Hitokoto>>

    @Query("SELECT COUNT(*) FROM hitokoto")
    fun countHitokoto(): Flowable<Long>
}