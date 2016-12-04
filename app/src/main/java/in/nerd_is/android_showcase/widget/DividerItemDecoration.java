package in.nerd_is.android_showcase.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/**
 * from https://gist.github.com/lapastillaroja/858caf1a82791b6c1a36
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    private Drawable dividerDrawable;
    private int dividerSize = -1;
    private int orientation;

    private boolean showFirstDivider = false;
    private boolean showLastDivider = false;

    private Rect bounds = new Rect();

    public DividerItemDecoration(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        dividerDrawable = new ColorDrawable(Color.TRANSPARENT);
        a.recycle();

        this.orientation = orientation;
    }

    public DividerItemDecoration(Context context, int orientation, int dividerResId) {
        this(context, orientation);
        dividerDrawable = ContextCompat.getDrawable(context, dividerResId);
    }

    public DividerItemDecoration(Context context, int orientation,
                                 int dividerResId, int dividerSizeInDp) {
        this(context, orientation, dividerResId);
        dividerSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dividerSizeInDp, context.getResources().getDisplayMetrics());
    }

    public void setShowFirstDivider(boolean showFirstDivider) {
        this.showFirstDivider = showFirstDivider;
    }

    public void setShowLastDivider(boolean showLastDivider) {
        this.showLastDivider = showLastDivider;
    }

    public void setDrawable(@NonNull Drawable dividerDrawable) {
        this.dividerDrawable = dividerDrawable;
    }

    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL && orientation != VERTICAL) {
            throw new IllegalArgumentException(
                    "Invalid orientation. It should be either HORIZONTAL or VERTICAL");
        }
        this.orientation = orientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        if (dividerDrawable == null)
            return;

        final int position = parent.getChildAdapterPosition(view);
        final int itemCount = parent.getAdapter().getItemCount();

        if (orientation == VERTICAL) {
            if (dividerSize == -1)
                dividerSize = dividerDrawable.getIntrinsicHeight();

            if (showFirstDivider && position == 0) {
                outRect.set(0, dividerSize, 0, dividerSize);
            } else if (showLastDivider || position != itemCount - 1) {
                outRect.bottom = dividerSize;
            }
        } else {
            if (dividerSize == -1)
                dividerSize = dividerDrawable.getIntrinsicWidth();

            if (showFirstDivider && position == 0) {
                outRect.set(dividerSize, 0, dividerSize, 0);
            } else if (showLastDivider || position != itemCount - 1){
                outRect.right = dividerSize;
            }
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || dividerDrawable == null)
            return;

        if (orientation == VERTICAL)
            drawVertical(c, parent);
        else
            drawHorizontal(c, parent);
    }

    @SuppressLint("NewApi")
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();

        final int left;
        final int right;
        if (parent.getClipToPadding()) {
            left = parent.getPaddingLeft();
            right = parent.getWidth() - parent.getPaddingRight();
            canvas.clipRect(left, parent.getPaddingTop(), right,
                    parent.getHeight() - parent.getPaddingBottom());
        } else {
            left = 0;
            right = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, bounds);
            int bottom;
            int top;

            if (i == 0 && showFirstDivider) {
                top = bounds.top + Math.round(ViewCompat.getTranslationY(child));
                bottom = top + dividerSize;
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.draw(canvas);
            }

            if (i == childCount - 1 && !showLastDivider)
                continue;

            bottom = bounds.bottom + Math.round(ViewCompat.getTranslationY(child));
            top = bottom - dividerSize;
            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(canvas);
        }

        canvas.restore();
    }

    @SuppressLint("NewApi")
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();

        final int top;
        final int bottom;
        if (parent.getClipToPadding()) {
            top = parent.getPaddingTop();
            bottom = parent.getHeight() - parent.getPaddingBottom();
            canvas.clipRect(parent.getPaddingLeft(), top,
                    parent.getWidth() - parent.getPaddingRight(), bottom);
        } else {
            top = 0;
            bottom = parent.getWidth();
        }

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getLayoutManager().getDecoratedBoundsWithMargins(child, bounds);
            int right;
            int left;

            if (i == 0 && showFirstDivider) {
                left = bounds.left + Math.round(ViewCompat.getTranslationX(child));
                right = left + dividerSize;
                dividerDrawable.setBounds(left, top, right, bottom);
                dividerDrawable.draw(canvas);
            }

            if (i == childCount - 1 && !showLastDivider)
                continue;

            right = bounds.right + Math.round(ViewCompat.getTranslationX(child));
            left = bottom - dividerSize;
            dividerDrawable.setBounds(left, top, right, bottom);
            dividerDrawable.draw(canvas);
        }

        canvas.restore();
    }
}