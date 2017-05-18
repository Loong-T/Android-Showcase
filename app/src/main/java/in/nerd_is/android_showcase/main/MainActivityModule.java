
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

package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyList;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListPresenter;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Module
public class MainActivityModule {

    private MainContract.View view;

    public MainActivityModule(MainActivity activity) {
        this.view = activity;
    }

    @Provides
    @ActivityScope
    MainContract.View provideView() {
        return view;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(GetHitokoto getHitokoto) {
        return new MainPresenter(getHitokoto);
    }

    @Provides
    @ActivityScope
    ZhihuDailyListPresenter provideZhihuDailyListPresenter(GetZhihuDailyList getZhihuDailyList) {
        return new ZhihuDailyListPresenter(getZhihuDailyList);
    }
}
