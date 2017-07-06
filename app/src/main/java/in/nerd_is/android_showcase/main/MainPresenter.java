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

import javax.inject.Inject;

import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Xuqiang ZHENG on 2016/9/20.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private GetHitokoto getHitokoto;

    @Inject
    public MainPresenter(GetHitokoto getHitokoto) {
        this.getHitokoto = getHitokoto;
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void loadHitokoto() {
        getHitokoto.execute(null, new DisposableSingleObserver<Hitokoto>() {
            @Override
            public void onSuccess(Hitokoto hitokoto) {
                view.showHitokoto(hitokoto);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }
        });
    }

    @Override
    public void cancelTask() {
        getHitokoto.cancel();
    }
}
