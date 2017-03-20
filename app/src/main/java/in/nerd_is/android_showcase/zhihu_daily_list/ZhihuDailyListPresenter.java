package in.nerd_is.android_showcase.zhihu_daily_list;

import com.annimon.stream.Stream;

import java.util.List;

import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyList;
import io.reactivex.observers.DisposableSingleObserver;

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
            public void onSuccess(List<?> objects) {
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
