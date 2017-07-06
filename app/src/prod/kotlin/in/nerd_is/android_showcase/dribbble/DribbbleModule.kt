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

package `in`.nerd_is.android_showcase.dribbble

import `in`.nerd_is.android_showcase.common.Constant.*
import `in`.nerd_is.android_showcase.dribbble.model.repository.DribbbleDataSource
import `in`.nerd_is.android_showcase.dribbble.model.repository.remote.DribbbleRemoteRepository
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import retrofit2.Retrofit
import javax.inject.Named

/**
 * @author Xuqiang ZHENG on 2017/6/20.
 */
@Module
class DribbbleModule {
    @Named(TAG_REMOTE)
    @Provides
    fun provideDribbleDataSource(@Named(TAG_DRIBBBLE) retrofit: Retrofit,
                                 @Named(TAG_IO) ioScheduler: Scheduler,
                                 @Named(TAG_MAIN) mainScheduler: Scheduler): DribbbleDataSource {
        return DribbbleRemoteRepository(retrofit, ioScheduler, mainScheduler)
    }
}