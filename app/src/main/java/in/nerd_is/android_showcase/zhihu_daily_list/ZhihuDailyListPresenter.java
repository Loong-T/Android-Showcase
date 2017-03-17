package in.nerd_is.android_showcase.zhihu_daily_list;

import com.annimon.stream.Stream;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyList;
import rx.Subscriber;

/**
 * @author Xuqiang ZHENG on 2016/11/20.
 */
public class ZhihuDailyListPresenter implements ZhihuDailyListContract.Presenter {

    private ZhihuDailyListContract.View view;
    private final GetZhihuDailyList getZhihuDailyList;

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
        getZhihuDailyList.execute(null,
                view.lifecycleTransformer(), new StorySubscriber(view));
    }

    @Override
    public void loadMoreStories(List<?> data) {
        List<Date> dates = Stream.of(data)
                .select(Date.class)
                .toList();
        Date date = dates.get(dates.size() - 1);

        getZhihuDailyList.execute(date, view.lifecycleTransformer(), new Subscriber<List<Object>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                view.toast(e.getLocalizedMessage());
            }

            @Override
            public void onNext(List<Object> data) {
                view.appendStories(data);
            }
        });
    }

    private static class StorySubscriber extends Subscriber<List<Object>> {

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
        public void onNext(List<Object> data) {
            view.showLatestStories(data);
        }
    }
}
