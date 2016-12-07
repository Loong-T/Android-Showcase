package in.nerd_is.android_showcase.common.di.activity;

import android.app.Activity;

import dagger.MembersInjector;

public interface ActivityComponent<A extends Activity> extends MembersInjector<A> {
}