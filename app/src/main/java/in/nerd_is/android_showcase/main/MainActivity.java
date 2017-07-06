package in.nerd_is.android_showcase.main;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.common.Configuration;
import in.nerd_is.android_showcase.dribbble_shots.DribbbleShotsFragment;
import in.nerd_is.android_showcase.hitokoto.model.Hitokoto;
import in.nerd_is.android_showcase.utils.AndroidUtils;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListFragment;

public class MainActivity extends BaseActivity
        implements MainContract.View, HasSupportFragmentInjector {

    private DrawerLayout drawer;
    private NavigationView navigationView;
    private TextView hitokotoTv;

    @Inject
    MainPresenter presenter;
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();

        presenter.loadHitokoto();

        showDefaultFragment();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.cancelTask();
        super.onDestroy();
    }

    private void initView() {
        Toolbar toolbar = find(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = find(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = find(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            changeFragment(item.getItemId());
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        hitokotoTv = ViewUtils.find(navigationView.getHeaderView(0), R.id.hitokoto_tv);

        ImageButton dayNightIb = ViewUtils.find(
                navigationView.getHeaderView(0), R.id.day_night_mode_ib);
        dayNightIb.setOnClickListener(v -> changeMode());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
            AndroidUtils.adjustViewAccordingToStatusBar(drawer, toolbar, dayNightIb);
        }
    }

    @Inject
    @Override
    public void setupPresenter() {
        presenter.setView(this);
    }

    @Override
    public void showHitokoto(Hitokoto hitokoto) {
        hitokotoTv.setText(hitokoto.getText());
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void changeFragment(@IdRes int id) {
        Fragment fragment;
        int themeColor;
        int configPage;
        switch (id) {
            case R.id.zhihu_menu_nav:
                fragment = new ZhihuDailyListFragment();
                themeColor = getCompatColor(R.color.zhihu_primary);
                configPage = Configuration.PAGE_ZHIHU;
                break;
            case R.id.dribbble_menu_nav:
                fragment = new DribbbleShotsFragment();
                themeColor = getCompatColor(R.color.dribbble_primary);
                configPage = Configuration.PAGE_DRIBBBLE;
                break;
            default:
                throw new IllegalStateException("Unknown page");
        }

        configuration.setDefaultMainPage(configPage);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main_activity, fragment)
                .commit();
        changeThemeColor(themeColor, themeColor);
    }

    private void showDefaultFragment() {
        int menuId;
        switch (configuration.getDefaultMainPage()) {
            case Configuration.PAGE_ZHIHU:
                menuId = R.id.zhihu_menu_nav;
                break;
            case Configuration.PAGE_DRIBBBLE:
                menuId = R.id.dribbble_menu_nav;
                break;
            default:
                throw new IllegalStateException("Unknown page");
        }
        navigationView.setCheckedItem(menuId);
        changeFragment(menuId);
    }
}
