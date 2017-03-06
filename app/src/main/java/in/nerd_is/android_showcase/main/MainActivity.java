package in.nerd_is.android_showcase.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import javax.inject.Inject;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily_list.ZhihuDailyListFragment;
import rx.Observable;

public class MainActivity extends BaseActivity implements MainContract.View {

    private DrawerLayout drawer;
    private TextView hitokotoTv;

    @Inject
    MainPresenter presenter;

    public MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();

//        presenter.loadHitokoto();

        showFragment();
    }

    private void initView() {
        Toolbar toolbar = find(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = find(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = find(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

        hitokotoTv = ViewUtils.find(navigationView.getHeaderView(0), R.id.hitokoto_tv);
    }

    @Override @Inject
    public void setupPresenter() {
        presenter.setView(this);
    }

    @Override
    public Observable.Transformer lifecycleTransformer() {
        return bindUntilDestroy();
    }

    private void showFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_main_activity, new ZhihuDailyListFragment())
                .commit();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        mainComponent = appComponent.mainComponentBuilder()
                .mainModule(new MainModule(this))
                .build();
        mainComponent.inject(this);
    }

//    @Override
//    public void showHitokoto(Hitokoto hitokoto) {
//        hitokotoTv.setText(hitokoto.getText());
//    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
