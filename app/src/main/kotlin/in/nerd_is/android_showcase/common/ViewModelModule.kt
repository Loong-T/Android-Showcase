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

package `in`.nerd_is.android_showcase.common

import `in`.nerd_is.android_showcase.common.di.annotation.ViewModelKey
import `in`.nerd_is.android_showcase.dribbble.viewmodel.DaggerViewModelFactory
import `in`.nerd_is.android_showcase.dribbble_shots.viewmodel.ShotsViewModel
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Xuqiang ZHENG on 2017/6/22.
 */
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShotsViewModel::class)
    abstract fun provideShotsViewModel(shotsViewModel: ShotsViewModel): ViewModel

    @Binds
    abstract fun provideViewModelFactory(
            daggerViewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}