package com.qiaobutang.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * from https://gist.github.com/lapastillaroja/858caf1a82791b6c1a36
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mDividerSize = -1;
    private boolean mShowFirstDivider = false;
    private boolean mShowLastDivider = false;


    public DividerItemDecoration(Context context, AttributeSet attrs) {
        final TypedArray a = context
                .obtainStyledAttributes(attrs, new int[]{android.R.attr.listDivider});
        mDivider = new ColorDrawable(Color.TRANSPARENT);
        a.recycle();
    }

    public DividerItemDecoration(Context context, AttributeSet attrs, boolean showFirstDivider,
            boolean showLastDivider) {
        this(context, attrs);
        mShowFirstDivider = showFirstDivider;
        mShowLastDivider = showLastDivider;
    }

    public DividerItemDecoration(Context context, int dividerResId) {
        mDivider = context.getResources().getDrawable(dividerResId);
    }

    public DividerItemDecoration(Context context, int dividerSize,
                                 boolean showFirstDivider, boolean showLastDivider) {
        this(context, null);
        mDividerSize = dividerSize;
        mShowFirstDivider = showFirstDivider;
        mShowLastDivider = showLastDivider;
    }

    public DividerItemDecoration(Context context, int dividerResId, int dividerSizeInDp,
                                 boolean showFirstDivider, boolean showLastDivider) {
        this(context, dividerResId);
        mDividerSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dividerSizeInDp, context.getResources().getDisplayMetrics());
        mShowFirstDivider = showFirstDivider;
        mShowLastDivider = showLastDivider;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
            RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (mDivider == null)
            return;
        if (parent.getChildLayoutPosition(view) < 1)
            return;
        if (mDividerSize < 0)
            return;

        boolean reverse = isReverse(parent);
        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {
            if (mDividerSize == 0)
                mDividerSize = mDivider.getIntrinsicHeight();

            if (reverse)
                outRect.bottom = mDividerSize;
            else
                outRect.top = mDividerSize;
        } else {
            if (mDividerSize == 0)
                mDividerSize = mDivider.getIntrinsicWidth();

            if (reverse)
                outRect.right = mDividerSize;
            else
                outRect.left = mDividerSize;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (mDivider == null) {
            super.onDraw(c, parent, state);
            return;
        }
        if (mDividerSize <= 0) {
            super.onDraw(c, parent, state);
            return;
        }

        // Initialization needed to avoid compiler warning
        int left = 0, right = 0, top = 0, bottom = 0;
        int orientation = getOrientation(parent);
        boolean reverse = isReverse(parent);
        int childCount = parent.getChildCount();

        if (orientation == LinearLayoutManager.VERTICAL) {
            if (reverse) {
                left = parent.getWidth() - parent.getPaddingLeft();
                right = parent.getPaddingRight();
            } else {
                left = parent.getPaddingLeft();
                right = parent.getWidth() - parent.getPaddingRight();
            }
        } else { //horizontal
            if (reverse) {
                top = parent.getHeight() - parent.getPaddingTop();
                bottom = parent.getPaddingBottom();
            } else {
                top = parent.getPaddingTop();
                bottom = parent.getHeight() - parent.getPaddingBottom();
            }
        }

        for (int i = mShowFirstDivider ? 0 : 1; i < childCount; i++) {
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            if (orientation == LinearLayoutManager.VERTICAL) {
                if (reverse) {
                    top = bottom + mDividerSize;
                    bottom = child.getBottom() - params.bottomMargin - mDividerSize;
                } else {
                    top = child.getTop() - params.topMargin - mDividerSize;
                    bottom = top + mDividerSize;
                }
            } else { //horizontal
                if (reverse) {
                    left = right + mDividerSize;
                    right = child.getRight() - params.rightMargin - mDividerSize;
                } else {
                    left = child.getLeft() - params.leftMargin - mDividerSize;
                    right = left + mDividerSize;
                }
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }

        // show last divider
        if (mShowLastDivider && childCount > 0) {
            View child = parent.getChildAt(childCount - 1);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            if (orientation == LinearLayoutManager.VERTICAL) {
                if (reverse) {
                    top = bottom + mDividerSize;
                    bottom = child.getTop() + params.topMargin - mDividerSize;
                } else {
                    top = child.getBottom() + params.bottomMargin - mDividerSize;
                    bottom = top + mDividerSize;
                }
            } else { // horizontal
                if (reverse) {
                    left = right + mDividerSize;
                    right = child.getLeft() + params.leftMargin - mDividerSize;
                } else {
                    left = child.getRight() + params.rightMargin - mDividerSize;
                    right = left + mDividerSize;
                }
            }
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else {
            throw new IllegalStateException(
                    "DividerItemDecoration can only be used with a LinearLayoutManager.");
        }
    }

    private boolean isReverse(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof  LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getReverseLayout();
        } else {
            throw new IllegalStateException(
                    "DividerItemDecoration can only be used with a LinearLayoutManager.");
        }
    }
}