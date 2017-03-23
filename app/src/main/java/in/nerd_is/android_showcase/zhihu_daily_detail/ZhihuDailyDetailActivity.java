package in.nerd_is.android_showcase.zhihu_daily_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.webkit.WebView;

import javax.inject.Inject;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.zhihu_daily.model.Story;

public class ZhihuDailyDetailActivity extends BaseActivity
        implements ZhihuDailyDetailContract.View {

    private static final String EXTRA_STORY = "extra_story";

    @Inject
    ZhihuDailyDetailPresenter presenter;

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_daily_detail_activity);

        setSupportActionBar(find(R.id.toolbar));
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        webView = find(R.id.web_view);

        Story story = getIntent().getParcelableExtra(EXTRA_STORY);
        actionBar.setTitle(story.title());

        presenter.loadDetail(story.id());
    }

    @Override
    public void showDetail(String detailHtml) {
        webView.loadDataWithBaseURL(null, detailHtml,
                "text/html", "utf-8", null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Inject
    @Override
    public void setupPresenter() {
        presenter.setView(this);
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        appComponent.zhihuDailyDetailComponentBuilder()
                .detailActivityModule(new ZhihuDailyDetailActivityModule())
                .build()
                .inject(this);
    }

    public static void start(Context context, @NonNull Story story) {
        Intent intent = new Intent(context, ZhihuDailyDetailActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        context.startActivity(intent);
    }
}
