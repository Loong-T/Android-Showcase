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
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xuqiang ZHENG on 2017/4/19.
 */
class DragToDismissHelper {

    boolean draggingDown = false;
    boolean draggingUp = false;

    private View view;
    private float totalDrag;

    private float distance = Float.MAX_VALUE;
    private float fraction = -1f;
    private float elasticity = 0.618f;
    private float scale = 1f;
    private boolean shouldScale = false;

    private List<DragToDismissCallback> callbacks;

    private DragToDismissHelper(Context context, AttributeSet attrs, View view) {
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.DragToDismissLayout, 0, 0);

        distance = a.getDimension(R.styleable
                .DragToDismissLayout_dragDismissDistance, distance);
        fraction = a.getFloat(R.styleable
                .DragToDismissLayout_dragDismissFraction, fraction);
        elasticity = a.getFloat(R.styleable
                .DragToDismissLayout_dragElasticity, elasticity);
        scale = a.getFloat(R.styleable
                .DragToDismissLayout_dragDismissScale, scale);
        shouldScale = scale != 1f;

        a.recycle();

        this.view = view;
    }

    static DragToDismissHelper create(@NonNull Context context,
                                      @Nullable AttributeSet attrs,
                                      @NonNull View view) {
        return new DragToDismissHelper(context, attrs, view);
    }

    void dragScale(int scroll) {
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
                    view.setPivotY(view.getHeight());
                }
            } else {
                draggingUp = true;
                if (shouldScale) {
                    view.setPivotY(0f);
                }
            }
        }
        // how far have we dragged relative to the distance to perform a dismiss
        // (0–1 where 1 = dismiss distance). Decreasing logarithmically as we approach the limit
        float dragFraction = (float) MathUtils.log2(1 + (absTotalDrag / distance));

        // calculate the desired translation given the drag fraction
        float dragTo = dragFraction * distance * elasticity;

        if (draggingUp) {
            // as we use the absolute magnitude when calculating the drag fraction, need to
            // re-apply the drag direction
            dragTo *= -1;
        }
        view.setTranslationY(dragTo);

        if (shouldScale && absTotalDrag > distance) {
            // 1 = log2(1 + distance / distance)
            final float scale = 1 - ((1 - this.scale) * (dragFraction - 1));
            view.setScaleX(scale);
            view.setScaleY(scale);
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
            view.setTranslationY(0f);
            view.setScaleX(1f);
            view.setScaleY(1f);
        }

        dispatchDragCallback(dragFraction, dragTo,
                Math.min(1f, Math.abs(totalDrag) / distance), totalDrag);
    }

    void finishOrCancel() {
        if (Math.abs(totalDrag) >= distance) {
            dispatchDismissCallback();
        } else { // settle back to natural position
            view.animate()
                    .translationY(0f)
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

    void onHeightChanged(int h) {
        if (fraction > 0f) {
            distance = h * fraction;
        }
    }

    void addListener(DragToDismissCallback listener) {
        if (callbacks == null) {
            callbacks = new ArrayList<>();
        }
        callbacks.add(listener);
    }

    void removeListener(DragToDismissCallback listener) {
        if (callbacks != null && callbacks.size() > 0) {
            callbacks.remove(listener);
        }
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
