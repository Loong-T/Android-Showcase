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

package in.nerd_is.android_showcase.hitokoto.model.repository.remote;

import javax.inject.Named;

import in.nerd_is.android_showcase.common.lib_support.retrofit.RetrofitUtils;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.model.repository.HitokotoDataSource;
import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.http.GET;

import static in.nerd_is.android_showcase.common.Constant.TAG_HITOKOTO;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public class HitokotoRemoteRepository implements HitokotoDataSource {

    private final Api api;

    public HitokotoRemoteRepository(@Named(TAG_HITOKOTO) Retrofit hitokotoRetrofit) {
        api = RetrofitUtils.create(hitokotoRetrofit, Api.class);
    }

    @Override
    public Single<Hitokoto> getHitokoto() {
        return api.getHitokoto();
    }

    @Override
    public Single<Long> saveHitokoto(Hitokoto hitokoto) {
        throw new UnsupportedOperationException("save function is not support on remote");
    }

    private interface Api {
        @GET(HitokotoUrl.ROOT)
        Single<Hitokoto> getHitokoto();
    }
}
