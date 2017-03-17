package in.nerd_is.android_showcase.common.widget;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = Integer.MIN_VALUE;
    private static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
    private static final long ID_HEADER = Long.MIN_VALUE;
    private static final long ID_FOOTER = Long.MIN_VALUE + 1;

    private final RecyclerView.Adapter wrappedAdapter;

    private View headerView;
    private View footerView;

    private boolean isHeaderShown = false;
    private boolean isFooterShown = false;
    private RecyclerView.LayoutManager layoutManager;

    public HeaderFooterAdapter(@NonNull RecyclerView.Adapter wrappedAdapter) {
        this.wrappedAdapter = wrappedAdapter;
        this.wrappedAdapter.registerAdapterDataObserver(adapterDataObserver);

        setHasStableIds(wrappedAdapter.hasStableIds());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        layoutManager = recyclerView.getLayoutManager();
        setupLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return wrappedAdapter.getItemCount() +
                (isHeaderShown ? 1 : 0) +
                (isFooterShown ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeader(position)) {
            return TYPE_HEADER;
        } else if (isFooter(position)) {
            return TYPE_FOOTER;
        } else {
            return wrappedAdapter.getItemViewType(getRealPosition(position));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER || viewType == TYPE_FOOTER) {
            return new HeaderFooterViewHolder(footerView);
        } else {
            return wrappedAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position,
                                 List<Object> payloads) {
        if (holder instanceof HeaderFooterViewHolder) {
            bind((HeaderFooterViewHolder) holder, position);
        } else {
            //noinspection unchecked
            wrappedAdapter.onBindViewHolder(holder, getRealPosition(position), payloads);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderFooterViewHolder) {
            bind((HeaderFooterViewHolder) holder, position);
        } else {
            //noinspection unchecked
            wrappedAdapter.onBindViewHolder(holder, getRealPosition(position));
        }
    }

    @Override
    public long getItemId(int position) {
        if (isHeader(position)) {
            return ID_HEADER;
        } else if (isFooter(position)) {
            return ID_FOOTER;
        } else {
            return wrappedAdapter.getItemId(getRealPosition(position));
        }
    }

    public boolean isHeader(int position) {
        return isHeaderShown && position == 0;
    }

    public boolean isFooter(int position) {
        return isFooterShown && position == getFooterPosition();
    }

    public void setHeaderView(@NonNull View headerView) {
        this.headerView = headerView;
    }

    public void setFooterView(@NonNull View footerView) {
        this.footerView = footerView;
    }

    public void showHeader() {
        isHeaderShown = true;
        notifyItemInserted(0);
    }

    public void showFooter() {
        isFooterShown = true;
        notifyItemInserted(getFooterPosition());
    }

    public void hideHeader() {
        isHeaderShown = false;
        notifyItemRemoved(0);
    }

    public void hideFooter() {
        isFooterShown = false;
        notifyItemRemoved(getFooterPosition());
    }

    public RecyclerView.Adapter getWrappedAdapter() {
        return wrappedAdapter;
    }

    public int getRealPosition(int position) {
        return position - (isHeaderShown ? 1 : 0);
    }

    private int getDelegatedPosition(int position) {
        return position + (isFooterShown ? 1 : 0);
    }

    private int getFooterPosition() {
        return wrappedAdapter.getItemCount() + (isFooterShown ? 1 : 0);
    }

    private void setupLayoutManager(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager castedLayoutManager = (GridLayoutManager) layoutManager;
            final SpanSizeLookup existingLookup = castedLayoutManager.getSpanSizeLookup();

            castedLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (isHeader(position) || isFooter(position)) {
                        return castedLayoutManager.getSpanCount();
                    }

                    return existingLookup.getSpanSize(getRealPosition(position));
                }
            });
        }
    }

    private void bind(HeaderFooterViewHolder holder, int position) {
        View viewToAdd = isHeader(position) ? headerView : footerView;

        ViewGroup.LayoutParams layoutParams;

        if (layoutManager instanceof StaggeredGridLayoutManager) {
            if (viewToAdd.getLayoutParams() == null) {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            } else {
                layoutParams = new StaggeredGridLayoutManager.LayoutParams(
                        viewToAdd.getLayoutParams().width,
                        viewToAdd.getLayoutParams().height
                );
            }

            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        } else {
            if (viewToAdd.getLayoutParams() == null) {
                layoutParams = new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
            } else {
                layoutParams = new ViewGroup.LayoutParams(
                        viewToAdd.getLayoutParams().width,
                        viewToAdd.getLayoutParams().height
                );
            }
        }

        holder.itemView.setLayoutParams(layoutParams);
    }

    private final RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        public void onChanged() {
            notifyDataSetChanged();
        }

        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(getDelegatedPosition(positionStart), itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            notifyItemRangeChanged(getDelegatedPosition(positionStart), itemCount, payload);
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(getDelegatedPosition(positionStart), itemCount);
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(getDelegatedPosition(positionStart), itemCount);
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            notifyItemRangeChanged(getDelegatedPosition(fromPosition),
                    getDelegatedPosition(toPosition) + itemCount);
        }
    };

    private static class HeaderFooterViewHolder extends RecyclerView.ViewHolder {

        HeaderFooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}