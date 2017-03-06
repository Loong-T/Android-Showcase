package in.nerd_is.android_showcase.zhihu_daily_list;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyStory;
import rx.Subscriber;

/**
 * @author Xuqiang ZHENG on 2016/11/20.
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
    public void loadLatestStories() {
        view.refreshing(true);
        getZhihuDailyStory.execute(null,
                view.lifecycleTransformer(), new StorySubscriber(view));
    }

    private static class StorySubscriber extends Subscriber<List<?>> {

        ZhihuDailyListContract.View view;

        StorySubscriber(ZhihuDailyListContract.View view) {
            this.view = view;
        }

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
        public void onNext(List<?> data) {
            view.showLatestStories(data);
        }
    }
}
