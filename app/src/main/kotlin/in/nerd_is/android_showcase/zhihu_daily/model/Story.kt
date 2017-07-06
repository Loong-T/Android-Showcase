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

package `in`.nerd_is.android_showcase.zhihu_daily.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import org.threeten.bp.LocalDate
import paperparcel.PaperParcel

/**
 * @author Xuqiang ZHENG on 2017/6/12.
 */
@PaperParcel
@Entity
data class Story(@PrimaryKey val id: Long,
                 val type: Int = 0,
                 val title: String,
                 val image: String?,
                 val images: List<String>?,
                 val read: Boolean = false) : Parcelable {

    var date: LocalDate? = null

    companion object {
        @JvmField val CREATOR = PaperParcelStory.CREATOR
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelStory.writeToParcel(this, dest, flags)
    }

    override fun describeContents() = 0
}