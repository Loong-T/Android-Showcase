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

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import in.nerd_is.android_showcase.TestUtils;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import io.reactivex.observers.DisposableSingleObserver;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.verify;

/**
 * @author Xuqiang ZHENG on 2016/10/7.
 */
public class MainPresenterTest {

    private MainPresenter presenter;

    @Mock
    private MainContract.View view;
    @Mock
    private GetHitokoto getHitokoto;

    @Captor
    private ArgumentCaptor<DisposableSingleObserver<Hitokoto>> observerCaptor;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new MainPresenter(getHitokoto);
        presenter.setView(view);
    }

    @Test
    public void loadHitokotoAndShow_allRight_hitokotoShowed() {
        Hitokoto hitokoto = TestUtils.generateHitokoto();

        presenter.loadHitokoto();

        verify(getHitokoto).execute(isNull(), observerCaptor.capture());
        observerCaptor.getValue().onSuccess(hitokoto);

        verify(view).showHitokoto(hitokoto);
    }

    @Test
    public void loadHitokotoAndShow_errorHappens_errorMessageShowed() {
        presenter.loadHitokoto();

        Exception exception = new Exception("error message");
        verify(getHitokoto).execute(isNull(), observerCaptor.capture());
        observerCaptor.getValue().onError(exception);

        verify(view).showError(exception);
    }
}
