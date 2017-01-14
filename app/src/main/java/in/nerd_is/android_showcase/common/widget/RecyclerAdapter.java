package in.nerd_is.android_showcase.common.widget;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import java.util.Collections;
import java.util.List;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.entity.RecyclerData;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.Date;
import in.nerd_is.android_showcase.zhihu_daily.entity.Story;

import static in.nerd_is.android_showcase.common.entity.RecyclerData.TYPE_ZHIHU_DAILY_DATE;
import static in.nerd_is.android_showcase.common.entity.RecyclerData.TYPE_ZHIHU_DAILY_ITEM;

/**
 * Created by Xuqiang ZHENG on 2016/11/23.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private final HolderFactory holderFactory;
    private final LayoutInflater inflater;
    @SuppressWarnings("unchecked")
    private List<RecyclerData> data = Collections.EMPTY_LIST;

    RecyclerAdapter(Context context, HolderFactory holderFactory) {
        this.context = context;
        this.holderFactory = holderFactory;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return data.size();
}

    @Override
    public int getItemViewType(int position) {
        return data.get(position).dataType();
    }

    @SuppressWarnings("unchecked")
    @Override
    public ViewHolder<? extends RecyclerData> onCreateViewHolder(ViewGroup parent, int viewType) {
        return holderFactory.createViewHolder(viewType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.render(data.get(position));
    }

    public void swap(List<RecyclerData> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
