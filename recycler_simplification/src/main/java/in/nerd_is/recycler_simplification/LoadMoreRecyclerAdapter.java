package in.nerd_is.recycler_simplification;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author Xuqiang ZHENG on 2017/3/16.
 */
public class LoadMoreRecyclerAdapter extends RecyclerAdapter {

    private LoadMore loadMore = new LoadMore();
    private OnLoadMoreListener listener;

    private boolean isLoading;
    private boolean shouldLoadMore;
    private RecyclerView.LayoutManager layoutManager;

    public LoadMoreRecyclerAdapter(@NonNull TypeFactory typeFactory,
                                   @LayoutRes final int loadMoreLayoutResId,
                                   @NonNull OnLoadMoreListener listener) {
        super(typeFactory);
        this.listener = listener;

        typeFactory.add(LoadMore.class, new ViewHolderCreator() {
            @Override
            public ViewHolder create(LayoutInflater inflater, ViewGroup parent) {
                return new LoadMoreHolder(inflater.inflate(
                        loadMoreLayoutResId, parent, false));
            }
        });
    }

    @Override
    public int getItemCount() {
        return shouldLoadMore ? getOriginCount() + 1 : getOriginCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getOriginCount()) {
            return typeFactory.getType(LoadMore.class);
        }
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int count = getOriginCount();
        if (position == count) {
            if (!isLoading) {
                isLoading = true;
                listener.loadMore(getData());
            }


            typeFactory.bindViewHolder(holder, loadMore);
            return;
        }

        super.onBindViewHolder(holder, position);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        layoutManager = recyclerView.getLayoutManager();

        if (layoutManager == null) {
            throw new IllegalStateException("please set a layout manager first");
        }

        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (getItemViewType(position) == typeFactory.getType(LoadMore.class)) {
                        return gridLayoutManager.getSpanCount();
                    } else {
                        return 1;
                    }
                }
            });
        }
    }

    @Override
    public void onViewAttachedToWindow(ViewHolder holder) {
        if (holder instanceof LoadMoreHolder
                && layoutManager instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager.LayoutParams lp =
                    (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            lp.setFullSpan(true);
        }
    }

    public void startEndlessLoadMore() {
        shouldLoadMore = true;
        if (getOriginCount() == getItemCount() - 1) {
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void stopEndlessLoadMore() {
        shouldLoadMore = false;
        if (getOriginCount() + 1 == getItemCount()) {
            notifyItemRemoved(getItemCount() - 1);
        }
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    @Override
    public void append(@NonNull List<?> list) {
        int startPos = data.size();
        data.addAll(list);
        notifyItemRangeInserted(startPos, list.size());
    }

    private int getOriginCount() {
        return super.getItemCount();
    }

    public interface OnLoadMoreListener {
        void loadMore(List<?> data);
    }

    private static final class LoadMore {
    }

    private static final class LoadMoreHolder extends ViewHolder<LoadMore> {

        LoadMoreHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void render(@NonNull LoadMore data) {
            // empty
        }
    }
}
