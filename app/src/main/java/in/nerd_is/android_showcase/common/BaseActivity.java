package in.nerd_is.android_showcase.common;

import android.support.annotation.IdRes;
import android.view.View;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.View {

    @SuppressWarnings("unchecked")
    protected <T extends View> T find(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }
}
