/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package in.nerd_is.android_showcase.image_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.Arrays;
import java.util.List;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;
import in.nerd_is.android_showcase.utils.AndroidUtils;
import in.nerd_is.dragtodismisslayout.DefaultDismissAnimator;
import in.nerd_is.dragtodismisslayout.DragToDismissLayout;

import static in.nerd_is.android_showcase.utils.CommonUtils.nullOrEmpty;

/**
 * @author Xuqiang ZHENG on 2017/4/4.
 */
public class ImageViewerActivity extends BaseActivity {

    public static final String EXTRA_URL_ARRAY = "extra_url_array";

    private List<String> urls;

    public static void start(Context context, @NonNull String url) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(EXTRA_URL_ARRAY, new String[] {url});
        context.startActivity(intent);
    }

    public static void start(Context context, @NonNull String[] urls) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(EXTRA_URL_ARRAY, urls);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer_activity);

        handleIntent();
        initView();
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

    @Override
    public void setupPresenter() {
        // empty
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        // empty
    }

    private void handleIntent() {
        Intent intent = getIntent();
        String[] arrayExtra = intent.getStringArrayExtra(EXTRA_URL_ARRAY);
        if (nullOrEmpty(arrayExtra)) {
            throw new IllegalArgumentException("Urls can not be null or empty");
        }
        urls = Arrays.asList(arrayExtra);
    }

    private void initView() {
        Toolbar toolbar = find(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(adapter.getPageTitle(0));
        AndroidUtils.adjustViewAccordingToStatusBar(contentView, toolbar);

        DragToDismissLayout dismissLayout = find(R.id.drag_to_dismiss_layout);
        dismissLayout.addListener(new DefaultDismissAnimator(this));

        ViewPager viewPager = find(R.id.view_pager);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setTitle(adapter.getPageTitle(position));
            }
        });
    }

    private final PagerAdapter adapter = new PagerAdapter() {

        @Override
        public int getCount() {
            return urls.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            String url = urls.get(position);
            PhotoView photoView = (PhotoView) getLayoutInflater().inflate(
                    R.layout.image_viewer_item, container, false);
            photoView.setAllowParentInterceptOnEdge(true);

            container.addView(photoView);

            Glide.with(ImageViewerActivity.this)
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(photoView);

            return photoView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public CharSequence getPageTitle(int position) {
            return String.format("%d / %d", position + 1, getCount());
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };
}
