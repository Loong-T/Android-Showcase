package in.nerd_is.android_showcase.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.ThisApplication;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.hitokoto.entity.Hitokoto;
import in.nerd_is.android_showcase.utils.ViewUtils;

public class MainActivity extends BaseActivity
        implements MainContract.View {

    private DrawerLayout drawer;
    private TextView hitokotoTv;

    @Inject MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        initView();
        inject();

        presenter.loadHitokoto();
    }

    public void initView() {
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

    private void inject() {
        ThisApplication.INSTANCE.appComponent
                .mainComponentBuilder()
                .mainModule(new MainModule(this, this.bindUntilDestory()))
                .build()
                .inject(this);
    }

    @Override
    public void showHitokoto(Hitokoto hitokoto) {
        hitokotoTv.setText(hitokoto.hitokoto);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
