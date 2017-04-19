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
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuqiang ZHENG on 2017/4/9.
 */
public class DragToDismissLayout extends FrameLayout {

    private static int STATE_IDLE = 0;
    private static int STATE_DRAGGING = 1;

    private int dragState = STATE_IDLE;
    private float dragDismissDistance = Float.MAX_VALUE;
    private float dragDismissFraction = -1f;
    private float dragDismissScale = 1f;
    private boolean shouldScale = false;
    private float dragElasticity = 0.618f;

    private float lastMotionY;
    private float totalDrag;
    private boolean draggingDown = false;
    private boolean draggingUp = false;

    private List<DragToDismissCallback> callbacks;

    public DragToDismissLayout(@NonNull Context context) {
        this(context, null, 0);
    }

    public DragToDismissLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragToDismissLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.DragToDismissLayout, 0, 0);

        dragDismissDistance = a.getDimension(
                R.styleable.DragToDismissLayout_dragDismissDistance, dragDismissDistance);
        dragDismissFraction = a.getFloat(
                R.styleable.DragToDismissLayout_dragDismissFraction, dragDismissFraction);
        dragDismissScale = a.getFloat(
                R.styleable.DragToDismissLayout_dragDismissScale, dragDismissScale);
        shouldScale = dragDismissScale != 1f;
        dragElasticity = a.getFloat(
                R.styleable.DragToDismissLayout_dragElasticity, dragElasticity);

        a.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (dragDismissFraction > 0f) {
            dragDismissDistance = h * dragDismissFraction;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int actionMasked = ev.getActionMasked();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                dragState = STATE_IDLE;
                lastMotionY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                dragState = STATE_DRAGGING;
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
            int scroll = (int) (lastMotionY - ev.getY());
            lastMotionY = ev.getY();

            dragScale(scroll);

            return true;
        }

        if (actionMasked == MotionEvent.ACTION_UP && dragState == STATE_DRAGGING) {
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

            dragState = STATE_IDLE;

            return true;
        }

        return super.onTouchEvent(ev);
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
}
