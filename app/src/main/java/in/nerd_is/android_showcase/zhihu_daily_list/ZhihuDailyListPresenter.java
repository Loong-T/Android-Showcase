package in.nerd_is.android_showcase.zhihu_daily_list;

import in.nerd_is.android_showcase.zhihu_daily.entity.LatestNews;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyStory;
import rx.Subscriber;

/**
 * Created by Xuqiang ZHENG on 2016/11/20.
 */
public class ZhihuDailyListPresenter implements ZhihuDailyListContract.Presenter {

    private ZhihuDailyListContract.View view;
    private final GetZhihuDailyStory getZhihuDailyStory;

    public ZhihuDailyListPresenter(GetZhihuDailyStory getZhihuDailyStory) {
        this.getZhihuDailyStory = getZhihuDailyStory;
    }

    @Override
    public void setView(ZhihuDailyListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadStories() {
        view.refreshing(true);
        getZhihuDailyStory.execute(null, view.lifecycleTransformer(), new StorySubscriber());
    }

    private class StorySubscriber extends Subscriber<LatestNews> {
        @Override
        public void onCompleted() {
            view.refreshing(false);
        }

        @Override
        public void onError(Throwable e) {
            view.refreshing(false);
            view.toast(e.getLocalizedMessage());
        }

        @Override
        public void onNext(LatestNews latestNews) {
            view.showStories(latestNews.stories);
        }
    }
}
