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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.github.piasy.biv.view.BigImageView;

import in.nerd_is.android_showcase.AppComponent;
import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseActivity;

/**
 * @author Xuqiang ZHENG on 2017/4/4.
 */
public class ImageViewerActivity extends BaseActivity {

    public static final String EXTRA_URL = "extra_url";

    private BigImageView bigImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_viewer_activity);
        bigImageView = find(R.id.big_image_view);

        String url = getIntent().getStringExtra(EXTRA_URL);
        bigImageView.showImage(Uri.parse(url));
    }

    @Override
    public void setupPresenter() {
        // empty
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        // empty
    }

    public static void start(Context context, @NonNull String url) {
        Intent intent = new Intent(context, ImageViewerActivity.class);
        intent.putExtra(EXTRA_URL, url);
        context.startActivity(intent);
    }
}
