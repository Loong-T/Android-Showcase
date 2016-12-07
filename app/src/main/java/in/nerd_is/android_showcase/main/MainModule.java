package in.nerd_is.android_showcase.main;

import dagger.Module;
import dagger.Provides;
import in.nerd_is.android_showcase.common.di.activity.ActivityModule;
import in.nerd_is.android_showcase.common.di.annotation.ActivityScope;
import in.nerd_is.android_showcase.hitokoto.usecase.GetHitokoto;
import in.nerd_is.android_showcase.zhihu_daily.ZhihuDailyModule;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListPresenter;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyStory;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */
@Module(includes = { ZhihuDailyModule.class })
public class MainModule extends ActivityModule<MainActivity> {

    private MainContract.View view;

    public MainModule(MainActivity activity) {
        super(activity);
        this.view = activity;
    }

    @Provides @ActivityScope
    MainContract.View provideView() {
        return view;
    }

    @Provides @ActivityScope
    MainPresenter provideMainPresenter(GetHitokoto getHitokoto) {
        return new MainPresenter(getHitokoto);
    }

    @Provides @ActivityScope
    ZhihuDailyListPresenter provideZhihuDailyListPresenter(GetZhihuDailyStory getZhihuDailyStory) {
        return new ZhihuDailyListPresenter(getZhihuDailyStory);
    }
}
