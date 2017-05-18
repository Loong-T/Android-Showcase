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

package in.nerd_is.android_showcase.zhihu_daily;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.ZhihuDailyDataSource;
import in.nerd_is.android_showcase.zhihu_daily.model.repository.remote.FakeZhihuDailyRemoteRepository;

/**
 * @author Xuqiang ZHENG on 2016/11/24.
 */
@Module
public class ZhihuDailyModule {
    @Provides
    ZhihuDailyDataSource providesZhihuDailyDataSource() {
        return new FakeZhihuDailyRemoteRepository();
    }
}
