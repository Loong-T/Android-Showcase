package in.nerd_is.android_showcase.zhihu_daily_list;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.format.DateTimeFormatterBuilder;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.widget.ViewHolder;
import in.nerd_is.android_showcase.utils.ViewUtils;
import in.nerd_is.android_showcase.zhihu_daily.entity.Date;
import in.nerd_is.android_showcase.zhihu_daily.entity.Story;

/**
 * Created by Xuqiang ZHENG on 2017/1/14.
 */
public class ZhihuDailyHolderFactory {

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
