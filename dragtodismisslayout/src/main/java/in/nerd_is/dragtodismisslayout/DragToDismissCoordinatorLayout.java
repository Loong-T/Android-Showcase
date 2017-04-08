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
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuqiang ZHENG on 2017/4/7.
 */
public class DragToDismissCoordinatorLayout extends CoordinatorLayout {

    private float dragDismissDistance = Float.MAX_VALUE;
    private float dragDismissFraction = -1f;
    private float dragDismissScale = 1f;
    private boolean shouldScale = false;
    private float dragElasticity = 0.618f;

    private float totalDrag;
    private boolean draggingDown = false;
    private boolean draggingUp = false;

    private List<DragToDismissCallback> callbacks;

    public DragToDismissCoordinatorLayout(Context context) {
        this(context, null, 0);
    }

    public DragToDismissCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragToDismissCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DragToDismissCoordinatorLayout, 0, 0);

        dragDismissDistance = a.getDimension(
                R.styleable.DragToDismissCoordinatorLayout_dragDismissDistance, dragDismissDistance);
        dragDismissFraction = a.getFloat(
                R.styleable.DragToDismissCoordinatorLayout_dragDismissFraction, dragDismissFraction);
        dragDismissScale = a.getFloat(
                R.styleable.DragToDismissCoordinatorLayout_dragDismissScale, dragDismissScale);
        shouldScale = dragDismissScale != 1f;
        dragElasticity = a.getFloat(
                R.styleable.DragToDismissCoordinatorLayout_dragElasticity, dragElasticity);

        a.recycle();
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        super.onStartNestedScroll(child, target, nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        if (draggingDown && dy > 0 || draggingUp && dy < 0) {
            dragScale(dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        dragScale(dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);

        if (Math.abs(totalDrag) >= dragDismissDistance) {
            dispatchDismissCallback();
        } else { // settle back to natural position
            animate().translationY(0f)
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(200L)
                    .start();
            totalDrag = 0;
            draggingUp = false;
            draggingDown = false;
            dispatchDragCallback(0f, 0f, 0f, 0f);
        }
    }

    public void addListener(DragToDismissCallback listener) {
        if (callbacks == null) {
            callbacks = new ArrayList<>();
        }
        callbacks.add(listener);
    }

    public void removeListener(DragToDismissCallback listener) {
        if (callbacks != null && callbacks.size() > 0) {
            callbacks.remove(listener);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (dragDismissFraction > 0f) {
            dragDismissDistance = h * dragDismissFraction;
        }
    }

    private void dragScale(int scroll) {
        if (scroll == 0) {
            return;
        }

        totalDrag += scroll;
        float absTotalDrag = Math.abs(totalDrag);

        // track the direction & set the pivot point for scaling
        // don't double track i.e. if start dragging down and then reverse, keep tracking as
        // dragging down until they reach the 'natural' position
        if (!draggingUp && !draggingDown) {
            if (scroll < 0) {
                draggingDown = true;
                if (shouldScale) {
                    setPivotY(getHeight());
                }
            } else {
                draggingUp = true;
                if (shouldScale) {
                    setPivotY(0f);
                }
            }
        }
        // how far have we dragged relative to the distance to perform a dismiss
        // (0–1 where 1 = dismiss distance). Decreasing logarithmically as we approach the limit
        float dragFraction = (float) MathUtils.log2(1 + (absTotalDrag / dragDismissDistance));

        // calculate the desired translation given the drag fraction
        float dragTo = dragFraction * dragDismissDistance * dragElasticity;

        if (draggingUp) {
            // as we use the absolute magnitude when calculating the drag fraction, need to
            // re-apply the drag direction
            dragTo *= -1;
        }
        setTranslationY(dragTo);

        if (shouldScale && absTotalDrag > dragDismissDistance) {
            // 1 = log2(1 + dragDismissDistance / dragDismissDistance)
            final float scale = 1 - ((1 - dragDismissScale) * (dragFraction - 1));
            setScaleX(scale);
            setScaleY(scale);
        }

        // if we've reversed direction and gone past the settle point then clear the flags to
        // allow the list to get the scroll events & reset any transforms
        if ((draggingDown && totalDrag >= 0)
                || (draggingUp && totalDrag <= 0)) {
            totalDrag = 0;
            dragTo = 0;
            dragFraction = 0;
            draggingDown = false;
            draggingUp = false;
            setTranslationY(0f);
            setScaleX(1f);
            setScaleY(1f);
        }

        dispatchDragCallback(dragFraction, dragTo,
                Math.min(1f, Math.abs(totalDrag) / dragDismissDistance), totalDrag);
    }

    private void dispatchDragCallback(float elasticOffset, float elasticOffsetPixels,
                                      float rawOffset, float rawOffsetPixels) {
        if (callbacks != null && !callbacks.isEmpty()) {
            for (DragToDismissCallback callback : callbacks) {
                callback.onDrag(elasticOffset, elasticOffsetPixels,
                        rawOffset, rawOffsetPixels);
            }
        }
    }

    private void dispatchDismissCallback() {
        if (callbacks != null && !callbacks.isEmpty()) {
            for (DragToDismissCallback callback : callbacks) {
                callback.onDragDismissed();
            }
        }
    }

    public static abstract class DragToDismissCallback {

        /**
         * Called for each drag event.
         *
         * @param elasticOffset       Indicating the drag offset with elasticity applied i.e. may
         *                            exceed 1.
         * @param elasticOffsetPixels The elastically scaled drag distance in pixels.
         * @param rawOffset           Value from [0, 1] indicating the raw drag offset i.e.
         *                            without elasticity applied. A value of 1 indicates that the
         *                            dismiss distance has been reached.
         * @param rawOffsetPixels     The raw distance the user has dragged
         */
        void onDrag(float elasticOffset, float elasticOffsetPixels,
                    float rawOffset, float rawOffsetPixels) {
        }

        /**
         * Called when dragging is released and has exceeded the threshold dismiss distance.
         */
        void onDragDismissed() {
        }

    }

    /**
     * An {@link DragToDismissCallback} which fades system chrome (i.e. status bar and
     * navigation bar) whilst elastic drags are performed and
     * {@link Activity#finishAfterTransition() finishes} the activity when drag dismissed.
     */
    public static class DefaultDismissAnimator extends DragToDismissCallback {

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
}
