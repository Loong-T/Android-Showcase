package in.nerd_is.recycler_simplification;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
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
    public void append(List<?> list) {
        int startPos = data.size() - 1;
        data.addAll(list);
        notifyItemRangeInserted(startPos, list.size());
    }

    private int getOriginCount() {
        return super.getItemCount();
    }

    public interface OnLoadMoreListener {
        void loadMore(List<?> data);
    }

    private static final class LoadMore { }

    private static final class LoadMoreHolder extends ViewHolder<LoadMore> {

        public LoadMoreHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void render(@NonNull LoadMore data) {
            // empty
        }
    }
}
