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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * @author Xuqiang ZHENG on 2017/4/9.
 */
public class DragToDismissLayout extends FrameLayout {

    private static int STATE_IDLE = 0;
    private static int STATE_DRAGGING = 1;

    private int dragState = STATE_IDLE;
    private float initX;
    private float initY;
    private float lastY;
    private float minDragDistance;

    private DragToDismissHelper helper;

    public DragToDismissLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public DragToDismissLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragToDismissLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = DragToDismissHelper.create(context, attrs, this);
        minDragDistance = dp2Px(4);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        helper.onHeightChanged(h);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                dragState = STATE_IDLE;
                initX = ev.getX();
                initY = ev.getY();
                lastY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (ev.getPointerCount() == 1 &&
                        dragState != STATE_DRAGGING &&
                        distance(initX, initY, ev.getX(), ev.getY()) > minDragDistance) {
                    dragState = STATE_DRAGGING;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                dragState = STATE_IDLE;
                break;
            default:
                return false;
        }
        return dragState == STATE_DRAGGING;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();

        if (actionMasked == MotionEvent.ACTION_MOVE) {
            int scroll = (int) (lastY - ev.getY());
            lastY = ev.getY();
            helper.dragScale(scroll);

            return true;
        }

        if (actionMasked == MotionEvent.ACTION_UP && dragState == STATE_DRAGGING) {
            helper.finishOrCancel();
            dragState = STATE_IDLE;

            return true;
        }

        return super.onTouchEvent(ev);
    }

    public void addListener(DragToDismissCallback listener) {
        helper.addListener(listener);
    }

    public void removeListener(DragToDismissCallback listener) {
        helper.removeListener(listener);
    }

    private float distance(float x1, float y1, float x2, float y2) {
        float dx = x1 - x2;
        float dy = y1 - y2;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }

    private float dp2Px(float dp) {
        return dp / getResources().getDisplayMetrics().density;
    }
}
