package in.nerd_is.android_showcase.zhihu_daily_list;

import java.util.List;

import in.nerd_is.android_showcase.common.BaseContract;
import in.nerd_is.android_showcase.common.entity.RecyclerData;

/**
 * Created by Xuqiang ZHENG on 2016/10/23.
 */

public interface ZhihuDailyListContract {
    interface View extends BaseContract.View {

        void refreshing(boolean refreshing);

        void showList(List<RecyclerData> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadStories();
    }
}
