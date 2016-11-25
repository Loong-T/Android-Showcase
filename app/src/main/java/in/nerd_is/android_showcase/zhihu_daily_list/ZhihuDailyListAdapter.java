package in.nerd_is.android_showcase.zhihu_daily_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.Story;

/**
 * Created by Xuqiang ZHENG on 2016/11/23.
 */
public class ZhihuDailyListAdapter extends RecyclerView.Adapter<ZhihuDailyListAdapter.Holder> {

    private final Context context;
    private final LayoutInflater inflater;
    @SuppressWarnings("unchecked")
    private List<Story> data = Collections.EMPTY_LIST;

    ZhihuDailyListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(inflater.inflate(R.layout.zhihu_daily_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        final Story story = data.get(position);

        holder.title.setText(story.title);

        if (!story.images.isEmpty()) {
            Glide.with(context)
                    .load(story.images.get(0))
                    .into(holder.image);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void swap(List<Story> stories) {
        data = stories;
        notifyDataSetChanged();
    }

    static class Holder extends RecyclerView.ViewHolder {

        final TextView title;
        final ImageView image;

        Holder(View itemView) {
            super(itemView);

            title = ViewUtils.find(itemView, R.id.text_view);
            image = ViewUtils.find(itemView, R.id.image_view);
        }
    }
}
