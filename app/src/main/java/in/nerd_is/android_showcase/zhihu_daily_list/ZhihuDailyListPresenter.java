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

package in.nerd_is.android_showcase.zhihu_daily_list;

import com.annimon.stream.Stream;

import java.util.List;

import javax.inject.Inject;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyList;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Xuqiang ZHENG on 2016/11/20.
 */
public class ZhihuDailyListPresenter implements ZhihuDailyListContract.Presenter {

    private ZhihuDailyListContract.View view;
    private final GetZhihuDailyList getZhihuDailyList;

    @Inject
    public ZhihuDailyListPresenter(GetZhihuDailyList getZhihuDailyList) {
        this.getZhihuDailyList = getZhihuDailyList;
    }

    @Override
    public void setView(ZhihuDailyListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadLatestStories() {
        view.refreshing(true);
        getZhihuDailyList.execute(null, new DisposableSingleObserver<List<?>>() {
            @Override
            public void onSuccess(List<?> data) {
                view.refreshing(false);
                view.showLatestStories(data);
            }

            @Override
            public void onError(Throwable e) {
                view.refreshing(false);
                view.toast(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void loadMoreStories(List<?> data) {
        List<Date> dates = Stream.of(data)
                .select(Date.class)
                .toList();
        Date date = dates.get(dates.size() - 1);

        getZhihuDailyList.execute(date, new DisposableSingleObserver<List<?>>() {

            @Override
            public void onSuccess(List<?> data) {
                view.appendStories(data);
            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getLocalizedMessage());
            }
        });
    }

    @Override
    public void cancelTask() {
        getZhihuDailyList.cancel();
    }
}
