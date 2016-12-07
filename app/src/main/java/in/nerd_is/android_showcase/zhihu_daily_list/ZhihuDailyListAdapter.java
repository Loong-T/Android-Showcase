package in.nerd_is.android_showcase.zhihu_daily_list;

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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.entity.RecyclerData;
import in.nerd_is.android_showcase.common.widget.ViewHolder;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.Date;
import in.nerd_is.android_showcase.zhihu_daily.entity.Story;

import static in.nerd_is.android_showcase.common.entity.RecyclerData.TYPE_ZHIHU_DAILY_DATE;
import static in.nerd_is.android_showcase.common.entity.RecyclerData.TYPE_ZHIHU_DAILY_ITEM;

/**
 * Created by Xuqiang ZHENG on 2016/11/23.
 */
public class ZhihuDailyListAdapter extends RecyclerView.Adapter<ViewHolder> {

    private final Context context;
    private final LayoutInflater inflater;
    @SuppressWarnings("unchecked")
    private List<RecyclerData> data = Collections.EMPTY_LIST;

    ZhihuDailyListAdapter(Context context) {
        this.context = context;
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


    @Override
    public ViewHolder<? extends RecyclerData> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_ZHIHU_DAILY_DATE:
                return new DateHolder(inflater.inflate(
                        R.layout.zhihu_daily_list_item_date, parent, false));
            case TYPE_ZHIHU_DAILY_ITEM:
                return new StoryHolder(context,
                        inflater.inflate(R.layout.zhihu_daily_list_item_story, parent, false));
            default:
                throw new IllegalArgumentException("unknown view type: " + viewType);
        }
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

    private static class StoryHolder extends ViewHolder<Story> {

        private Context context;
        private final TextView tvTitle;
        private final ImageView ivImage;

        StoryHolder(Context context, View itemView) {
            super(itemView);
            this.context = context;
            tvTitle = ViewUtils.find(itemView, R.id.text_view);
            ivImage = ViewUtils.find(itemView, R.id.image_view);
        }

        @Override
        public void render(Story story) {
            tvTitle.setText(story.title);

            if (!story.images.isEmpty()) {
                Glide.with(context)
                        .load(story.images.get(0))
                        .into(ivImage);
            }
            ivImage.setContentDescription(context.getString(
                    R.string.content_desc_zhihu_daily_list_item_template, story.title));
        }
    }

    private static class DateHolder extends ViewHolder<Date> {

        private final static DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder().appendPattern("MMMM EEEE").toFormatter();

        private final TextView tvDate;

        public DateHolder(View itemView) {
            super(itemView);
            tvDate = ViewUtils.find(itemView, R.id.text_view);
        }

        @Override
        public void render(Date date) {
            tvDate.setText(FORMATTER.format(date.date));
        }
    }
}
