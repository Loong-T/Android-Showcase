package in.nerd_is.android_showcase.zhihu_daily_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import java.util.List;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily.model.Date;
import in.nerd_is.android_showcase.zhihu_daily.model.Story;
import in.nerd_is.recycler_simplification.TypeFactory;
import in.nerd_is.recycler_simplification.ViewHolder;

/**
 * @author Xuqiang ZHENG on 2017/2/26.
 */
public class ZhihuDailyListTypeFactory extends TypeFactory {

    private final OnClickListener listener;

    public ZhihuDailyListTypeFactory(OnClickListener itemClickedListener) {
        listener = itemClickedListener;
    }

    @Override
    protected void addTypeRules() {
        add(Date.class, DateHolder::newInstance);
        add(Story.class, (inflater, parent) -> StoryHolder.newInstance(inflater, parent, listener));
    }

    private static class StoryHolder extends ViewHolder<Story> {

        private final Context context;
        private final OnClickListener listener;
        private final TextView tvTitle;
        private final ImageView ivImage;

        StoryHolder(Context context, View itemView, OnClickListener listener) {
            super(itemView);
            this.context = context;
            this.listener = listener;
            tvTitle = ViewUtils.find(itemView, R.id.text_view);
            ivImage = ViewUtils.find(itemView, R.id.image_view);
        }

        public static StoryHolder newInstance(LayoutInflater inflater,
                                              ViewGroup parent,
                                              OnClickListener listener) {
            return new StoryHolder(parent.getContext(),
                    inflater.inflate(R.layout.zhihu_daily_list_item_story,
                            parent, false), listener);
        }

        @Override
        public void render(@NonNull Story story) {
            tvTitle.setText(story.getTitle());

            List<String> images = story.getImages();
            if (images != null && !images.isEmpty()) {
                Glide.with(context)
                        .load(images.get(0))
                        .into(ivImage);
            }
            ivImage.setContentDescription(context.getString(
                    R.string.content_desc_zhihu_daily_list_item_template,
                    story.getTitle()));

            itemView.setOnClickListener(v -> listener.onItemClicked(story));
        }
    }

    private static class DateHolder extends ViewHolder<Date> {

        private final static DateTimeFormatter FORMATTER =
                new DateTimeFormatterBuilder().appendPattern("MMMM d EEEE").toFormatter();

        private final TextView tvDate;

        public DateHolder(View itemView) {
            super(itemView);
            tvDate = ViewUtils.find(itemView, R.id.text_view);
        }

        public static DateHolder newInstance(LayoutInflater inflater, ViewGroup parent) {
            return new DateHolder(inflater.inflate(
                    R.layout.zhihu_daily_list_item_date, parent, false));
        }

        @Override
        public void render(@NonNull Date date) {
            tvDate.setText(FORMATTER.format(date.getDate()));
        }
    }

    public interface OnClickListener {
        void onItemClicked(Story story);
    }
}
