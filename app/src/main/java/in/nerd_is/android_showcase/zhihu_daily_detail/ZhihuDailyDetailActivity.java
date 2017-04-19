package in.nerd_is.android_showcase.zhihu_daily_detail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;

import net.opacapp.multilinecollapsingtoolbar.CollapsingToolbarLayout;

import javax.inject.Inject;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.common.lib_support.glide.PaletteBitmap;
import in.nerd_is.android_showcase.common.lib_support.glide.PaletteBitmapTranscoder;
import in.nerd_is.android_showcase.image_view.ImageViewerActivity;
import in.nerd_is.android_showcase.utils.AndroidUtils;
import in.nerd_is.android_showcase.zhihu_daily.model.Story;
import in.nerd_is.android_showcase.zhihu_daily.model.StoryDetail;
import in.nerd_is.dragtodismisslayout.DefaultDismissAnimator;
import in.nerd_is.dragtodismisslayout.DragToDismissCoordinatorLayout;

public class ZhihuDailyDetailActivity extends BaseActivity
        implements ZhihuDailyDetailContract.View {

    private static final String EXTRA_STORY = "extra_story";

    @Inject
    ZhihuDailyDetailPresenter presenter;

    private WebView webView;
    private ImageView headImage;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static void start(Context context, @NonNull Story story) {
        Intent intent = new Intent(context, ZhihuDailyDetailActivity.class);
        intent.putExtra(EXTRA_STORY, story);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // WebView has a bug with night mode, it will change ui mode while first creation
        // see https://code.google.com/p/android/issues/detail?id=226208 for more detail
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            new WebView(this);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.zhihu_daily_detail_activity);

        Story story = getIntent().getParcelableExtra(EXTRA_STORY);

        Toolbar toolbar = find(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(story.title());
        AndroidUtils.adjustViewAccordingToStatusBar(contentView, toolbar);

        webView = find(R.id.web_view);
        headImage = find(R.id.app_bar_image);
        collapsingToolbarLayout = find(R.id.collapsing_toolbar_layout);

        DragToDismissCoordinatorLayout dismissLayout = find(R.id.drag_to_dismiss_layout);
        dismissLayout.addListener(new DefaultDismissAnimator(this));

        presenter.loadDetail(story.id());
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    @Override
    public void showDetail(StoryDetail storyDetail) {
        TextView sourceTv = find(R.id.source_tv);
        sourceTv.setText(storyDetail.imageSource());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new JsProxy(this), "activity");
        webView.loadDataWithBaseURL(null, storyDetail.toHtml(configuration.isNightMode()),
                "text/html", "utf-8", null);

        Glide.with(this)
                .fromString()
                .asBitmap()
                .transcode(new PaletteBitmapTranscoder(this), PaletteBitmap.class)
                .load(storyDetail.image())
                .into(new ImageViewTarget<PaletteBitmap>(headImage) {
                    @Override
                    protected void setResource(PaletteBitmap resource) {
                        view.setImageBitmap(resource.bitmap);
                        changeThemeByPalette(resource.palette);
                    }
                });
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

    private void changeThemeByPalette(Palette palette) {
        boolean isNight = configuration.isNightMode();

        int defaultToolbarColor = getCompatColor(R.color.zhihu_primary);
        int toolbarColor;
        int statusBarColor;
        if (isNight) {
            toolbarColor = palette.getDarkMutedColor(defaultToolbarColor);
            statusBarColor = toolbarColor;
        } else {
            toolbarColor = palette.getLightVibrantColor(defaultToolbarColor);
            statusBarColor = toolbarColor;
        }

        collapsingToolbarLayout.setContentScrimColor(toolbarColor);
        collapsingToolbarLayout.setStatusBarScrimColor(statusBarColor);
    }

    private static class JsProxy {
        private ZhihuDailyDetailActivity activity;

        JsProxy(ZhihuDailyDetailActivity activity) {
            this.activity = activity;
        }

        @JavascriptInterface
        public void startImageViewer(String url) {
            ImageViewerActivity.start(activity, url);
        }
    }
}
