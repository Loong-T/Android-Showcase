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

package in.nerd_is.dragtodismisslayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.Window;

/**
 * An {@link DragToDismissCallback} which fades system chrome (i.e. status bar and
 * navigation bar) whilst elastic drags are performed and
 * {@link Activity#finishAfterTransition() finishes} the activity when drag dismissed.
 */
public class DefaultDismissAnimator extends DragToDismissCallback {

    private final Activity activity;
    private final boolean hasNavBar;
    private int statusBarAlpha;
    private int navBarAlpha;

    public DefaultDismissAnimator(Activity activity) {
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            statusBarAlpha = Color.alpha(activity.getWindow().getStatusBarColor());
            navBarAlpha = Color.alpha(activity.getWindow().getNavigationBarColor());
        }
        hasNavBar = ViewUtils.isNavBarOnBottom(activity);
    }

    @Override
    void onDrag(float elasticOffset, float elasticOffsetPixels,
                float rawOffset, float rawOffsetPixels) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            if (elasticOffsetPixels > 0) {
                // dragging downward, fade the status bar in proportion
                int statusBarColor = ColorUtils.modifyAlpha(
                        window.getStatusBarColor(), (int) ((1f - rawOffset) * statusBarAlpha));
                window.setStatusBarColor(statusBarColor);
            } else if (elasticOffsetPixels == 0) {
                // reset
                window.setStatusBarColor(ColorUtils.modifyAlpha(
                        window.getStatusBarColor(), statusBarAlpha));
                window.setNavigationBarColor(ColorUtils.modifyAlpha(
                        window.getNavigationBarColor(), navBarAlpha));
            } else if (hasNavBar) {
                // dragging upward, fade the navigation bar in proportion
                int navigationBarColor = ColorUtils.modifyAlpha(
                        window.getNavigationBarColor(), (int) ((1f - rawOffset) * navBarAlpha));
                window.setNavigationBarColor(navigationBarColor);
            }
        }
    }

    @Override
    void onDragDismissed() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.finishAfterTransition();
        } else {
            activity.finish();
        }
    }
}
