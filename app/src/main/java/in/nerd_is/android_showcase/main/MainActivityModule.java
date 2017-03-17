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
