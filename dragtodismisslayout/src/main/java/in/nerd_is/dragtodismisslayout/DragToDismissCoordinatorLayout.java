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

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Xuqiang ZHENG on 2017/4/7.
 */
public class DragToDismissCoordinatorLayout extends CoordinatorLayout {

    private DragToDismissHelper helper;

    public DragToDismissCoordinatorLayout(Context context) {
        this(context, null, 0);
    }

    public DragToDismissCoordinatorLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragToDismissCoordinatorLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        helper = DragToDismissHelper.create(context, attrs, this);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        super.onStartNestedScroll(child, target, nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        super.onNestedPreScroll(target, dx, dy, consumed);
        if (helper.draggingDown && dy > 0 || helper.draggingUp && dy < 0) {
            helper.dragScale(dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed,
                               int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
        helper.dragScale(dyUnconsumed);
    }

    @Override
    public void onStopNestedScroll(View child) {
        super.onStopNestedScroll(child);
        helper.finishOrCancel();
    }

    public void addListener(DragToDismissCallback listener) {
        helper.addListener(listener);
    }

    public void removeListener(DragToDismissCallback listener) {
        helper.removeListener(listener);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        helper.onHeightChanged(h);
    }
}
