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

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyList;
import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

/**
 * @author Xuqiang ZHENG on 2017/5/18.
 */
public class ZhihuDailyListPresenterTest {

    private ZhihuDailyListPresenter presenter;

    @Mock
    private ZhihuDailyListContract.View view;
    @Mock
    private GetZhihuDailyList getZhihuDailyList;

    @Captor
    private ArgumentCaptor<DisposableSingleObserver<List<?>>> observerCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new ZhihuDailyListPresenter(getZhihuDailyList);
        presenter.setView(view);
    }

    @Test
    public void loadLatestStories() throws Exception {
        presenter.loadLatestStories();

        verify(getZhihuDailyList).execute(isNull(), observerCaptor.capture());
        observerCaptor.getValue().onSubscribe();
    }

    @Test
    public void loadMoreStories() throws Exception {

    }

}