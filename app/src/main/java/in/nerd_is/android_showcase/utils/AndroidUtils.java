package in.nerd_is.android_showcase.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Xuqiang ZHENG on 2017/4/2.
 */
public class AndroidUtils {
    public static int getStatusBarHeight(Context context) {
        int height = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            height = context.getResources().getDimensionPixelSize(resourceId);
        }
        return height;
    }

    @TargetApi(Build.VERSION_CODES.KITKAT_WATCH)
    public static void adjustViewAccordingToStatusBar(View container, View target) {
        container.setOnApplyWindowInsetsListener((v, insets) -> {
            ViewGroup.MarginLayoutParams lpToolbar =
                    (ViewGroup.MarginLayoutParams) target.getLayoutParams();
            lpToolbar.topMargin += insets.getSystemWindowInsetTop();
            lpToolbar.leftMargin += insets.getSystemWindowInsetLeft();
            lpToolbar.rightMargin += insets.getSystemWindowInsetRight();
            target.setLayoutParams(lpToolbar);

            // clear this listener so insets aren't re-applied
            container.setOnApplyWindowInsetsListener(null);

            return insets.consumeSystemWindowInsets();
        });
    }
}
