package in.nerd_is.android_showcase.common;


import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import com.trello.rxlifecycle.android.FragmentEvent;
import com.trello.rxlifecycle.components.support.RxFragment;

import rx.Observable;

/**
 * Created by Xuqiang ZHENG on 2016/10/23.
 */

public abstract class BaseFragment extends RxFragment implements BaseContract.View {

    @SuppressWarnings({"unchecked", "ConstantConditions"})
    protected <T extends View> T find(@IdRes int viewId) {
        return (T) getView().findViewById(viewId);
    }

    @Override
    public void toast(CharSequence text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void toast(@StringRes int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void snackbar(CharSequence text) {
        Snackbar.make(getView(), text, Snackbar.LENGTH_SHORT).show();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void snackbar(@StringRes int resId) {
        Snackbar.make(getView(), resId, Snackbar.LENGTH_SHORT).show();
    }

    protected Observable.Transformer bindUntilDestroy() {
        return bindUntilEvent(FragmentEvent.DESTROY);
    }
}
