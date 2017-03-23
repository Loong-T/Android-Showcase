package in.nerd_is.android_showcase.zhihu_daily_detail;

import in.nerd_is.android_showcase.common.BaseContract;
import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;

/**
 * @author Xuqiang ZHENG on 2017/3/21.
 */
public interface ZhihuDailyDetailContract {
    interface View extends BaseContract.View {

        void showDetail(String detailHtml);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadDetail(long id);
    }
}
