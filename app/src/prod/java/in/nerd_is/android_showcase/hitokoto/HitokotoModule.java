
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

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.AppDatabase;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import in.nerd_is.android_showcase.hitokoto.model.repository.local.HitokotoLocalRepository;
import in.nerd_is.android_showcase.hitokoto.model.repository.remote.HitokotoRemoteRepository;
import retrofit2.Retrofit;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;
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
    public static HitokotoDataSource provideRemoteDataSource(
            @Named(TAG_HITOKOTO) Retrofit hitokotoRetrofit) {
        return new HitokotoRemoteRepository(hitokotoRetrofit);
    }

    @Provides
    @ActivityScope
    @Named(TAG_LOCAL)
    public static HitokotoDataSource provideLocalDataSource(AppDatabase appDatabase) {
        return new HitokotoLocalRepository(appDatabase);
    }
}
