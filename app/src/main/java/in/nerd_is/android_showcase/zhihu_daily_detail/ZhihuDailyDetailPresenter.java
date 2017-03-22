package in.nerd_is.android_showcase.zhihu_daily_detail;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
public class ZhihuDailyDetailPresenter implements ZhihuDailyDetailContract.Presenter {

    private ZhihuDailyDetailContract.View view;

    @Override
    public void setView(ZhihuDailyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void cancelTask() {

    }
}
