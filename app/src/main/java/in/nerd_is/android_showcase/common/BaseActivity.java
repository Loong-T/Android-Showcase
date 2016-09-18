package in.nerd_is.android_showcase.common;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public class BaseActivity extends AppCompatActivity implements BaseContract.View {

    @SuppressWarnings("unchecked")
    protected <T extends View> T find(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }
}
