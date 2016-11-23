package in.nerd_is.android_showcase.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.trello.rxlifecycle.android.ActivityEvent;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import in.nerd_is.android_showcase.ThisApplication;
import in.nerd_is.android_showcase.common.di.activity.HasActivitySubcomponentBuilders;
import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/9/18.
 */
public abstract class BaseActivity extends RxAppCompatActivity implements BaseContract.View {

    protected ViewGroup contentView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = find(android.R.id.content);
        setupActivityComponent();
    }

    @SuppressWarnings("unchecked")
    protected <T extends View> T find(@IdRes int viewId) {
        return (T) findViewById(viewId);
    }

    @Override
    public void toast(CharSequence text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void snackbar(CharSequence text) {
        Snackbar.make(contentView, text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void snackbar(@StringRes int resId) {
        Snackbar.make(contentView, resId, Snackbar.LENGTH_SHORT).show();
    }

    public Observable.Transformer bindUntilDestroy() {
        return bindUntilEvent(ActivityEvent.DESTROY);
    }

    protected void setupActivityComponent() {
        inject((HasActivitySubcomponentBuilders) getApplicationContext());
    }

    protected abstract void inject(HasActivitySubcomponentBuilders builders);
}
