
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

package in.nerd_is.android_showcase.hitokoto;

import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.model.repository.FakeHitokotoRemoteRepository;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoDbHelper;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoLocalRepository;

import static in.nerd_is.android_showcase.common.Constant.TAG_LOCAL;
import static in.nerd_is.android_showcase.common.Constant.TAG_REMOTE;

/**
 * @author Xuqiang ZHENG on 2016/10/1.
 */
@Module
public class HitokotoModule {
    @Provides
    @ActivityScope
    @Named(TAG_REMOTE)
    public static HitokotoDataSource provideRemoteDataSource() {
        return new FakeHitokotoRemoteRepository();
    }

    @Provides
    @ActivityScope
    @Named(TAG_LOCAL)
    public static HitokotoDataSource provideLocalDataSource(Context context) {
        return new HitokotoLocalRepository(new HitokotoDbHelper(context));
    }
}
