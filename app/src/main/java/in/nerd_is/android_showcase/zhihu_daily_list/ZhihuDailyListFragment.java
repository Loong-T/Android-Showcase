package in.nerd_is.android_showcase.zhihu_daily_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseFragment;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/10/23.
 */

public class ZhihuDailyListFragment extends BaseFragment implements ZhihuDailyListContract.View {

    @Inject
    ZhihuDailyListContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhihu_daily_list_fragment, container, false);
    }

    @Override
    public Observable.Transformer lifecycleTransformer() {
        return bindUntilDestroy();
    }
}
