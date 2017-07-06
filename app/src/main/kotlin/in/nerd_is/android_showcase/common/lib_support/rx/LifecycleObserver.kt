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

package `in`.nerd_is.android_showcase.common.lib_support.rx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

/**
 * @author Xuqiang ZHENG on 2017/6/20.
 */
abstract class LifecycleSingleObserver<T>(val lifecycleOwner: LifecycleOwner) :
        SingleObserver<T>, LifecycleObserver {

    private lateinit var disposable: Disposable

    override fun onSubscribe(disposable: Disposable) {
        this.disposable = disposable
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun dispose() {
        disposable.dispose()
    }
}