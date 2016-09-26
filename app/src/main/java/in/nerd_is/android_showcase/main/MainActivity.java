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

        hitokotoTv = find(R.id.hitokoto_tv);
    }

    private void inject() {
        DaggerMainComponent.builder()
                .applicationComponent(ThisApplication.INSTANCE.appComponent)
                .mainModule(new MainModule(this))
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
