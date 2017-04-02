package in.nerd_is.android_showcase.common;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.ThisApplication;

/**
 * @author Xuqiang ZHENG on 2016/9/18.
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    protected ViewGroup contentView;
    protected ThisApplication thisApplication;
    protected Configuration configuration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contentView = find(android.R.id.content);
        thisApplication = (ThisApplication) getApplication();
        configuration = thisApplication.configuration;
        setupActivityComponent(thisApplication.appComponent);
    }

    protected abstract void setupActivityComponent(AppComponent appComponent);

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

    @Override
    public void showError(Throwable throwable) {
        String message = throwable.getLocalizedMessage();
        Log.e(getClass().getSimpleName(), message, throwable);
        toast(message);
    }

    protected void changeMode() {
        configuration.setNightMode(!configuration.isNightMode());
        thisApplication.setNightModeFromConfiguration();
        recreate();
    }

    protected void changeThemeColor(int actionBarColor, int statusBarBackgroundColor) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(actionBarColor));
        }
        AppBarLayout abl = find(R.id.app_bar_layout);
        if (abl != null) {
            abl.setBackgroundDrawable(new ColorDrawable(statusBarBackgroundColor));
        }
    }

    public @ColorInt int getCompatColor(@ColorRes int resId) {
        return ContextCompat.getColor(this, resId);
    }
}
