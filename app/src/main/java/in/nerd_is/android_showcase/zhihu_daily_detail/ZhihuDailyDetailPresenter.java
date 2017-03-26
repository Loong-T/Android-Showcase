package in.nerd_is.android_showcase.zhihu_daily_detail;

import javax.inject.Inject;

import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;
import in.nerd_is.android_showcase.zhihu_daily.usecase.GetZhihuDailyDetail;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
public class ZhihuDailyDetailPresenter implements ZhihuDailyDetailContract.Presenter {

    private ZhihuDailyDetailContract.View view;

    @Inject
    GetZhihuDailyDetail getZhihuDailyDetail;

    public ZhihuDailyDetailPresenter(GetZhihuDailyDetail getZhihuDailyDetail) {
        this.getZhihuDailyDetail = getZhihuDailyDetail;
    }

    @Override
    public void setView(ZhihuDailyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void loadDetail(long id) {
        getZhihuDailyDetail.execute(id, new DisposableSingleObserver<StoryDetail>() {
            @Override
            public void onSuccess(StoryDetail storyDetail) {
                view.showDetail(storyDetail);
            }

            @Override
            public void onError(Throwable e) {
                view.showError(e);
            }
        });
    }

    @Override
    public void cancelTask() {
        getZhihuDailyDetail.cancel();
    }
}
