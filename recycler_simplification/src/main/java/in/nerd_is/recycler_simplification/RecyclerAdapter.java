package in.nerd_is.recycler_simplification;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * @author Xuqiang ZHENG on 2016/11/23.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    protected final TypeFactory typeFactory;
    @SuppressWarnings("unchecked")
    @NonNull protected List<Object> data = Collections.EMPTY_LIST;

    public RecyclerAdapter(@NonNull TypeFactory typeFactory) {
        this.typeFactory = typeFactory;
    }

    @Override
    public int getItemCount() {
        return data.size();
}

    @Override
    public int getItemViewType(int position) {
        return typeFactory.getType(data.get(position).getClass());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return typeFactory.createViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        typeFactory.bindViewHolder(holder, data.get(position));
    }

    @NonNull
    public List<?> getData() {
        return data;
    }

    public final void swap(@NonNull List<?> list) {
        //noinspection unchecked
        this.data = (List<Object>) list;
        notifyDataSetChanged();
    }

    public void append(@NonNull List<?> list) {
        int startPos = data.size();
        data.addAll(list);
        notifyItemRangeInserted(startPos, list.size());
    }
}
