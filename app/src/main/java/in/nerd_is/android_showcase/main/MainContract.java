package in.nerd_is.android_showcase.main;

import in.nerd_is.android_showcase.common.BaseContract;

/**
 * Created by Xuqiang ZHENG on 2016/9/20.
 */

public interface MainContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter {
        void loadHitokoto();
    }
}
