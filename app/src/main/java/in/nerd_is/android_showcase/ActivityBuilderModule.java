
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

package in.nerd_is.android_showcase;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import in.nerd_is.android_showcase.hitokoto.HitokotoModule;
import in.nerd_is.android_showcase.main.MainActivity;
import in.nerd_is.android_showcase.main.MainFragmentBuilderModule;
import in.nerd_is.android_showcase.zhihu_daily.ZhihuDailyModule;
import in.nerd_is.android_showcase.zhihu_daily_detail.ZhihuDailyDetailActivity;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = {
            MainFragmentBuilderModule.class,
            HitokotoModule.class,
    })
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = {
            ZhihuDailyModule.class
    })
    abstract ZhihuDailyDetailActivity contributeZhihuDailyDetailActivity();
}
