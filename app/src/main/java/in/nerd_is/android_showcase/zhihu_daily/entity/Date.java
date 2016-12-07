package in.nerd_is.android_showcase.zhihu_daily.entity;

import org.threeten.bp.LocalDate;

import in.nerd_is.android_showcase.common.entity.RecyclerData;

/**
 * Created by Xuqiang ZHENG on 2016/12/5.
 */
public class Date implements RecyclerData {

    public LocalDate date;

    public Date(LocalDate date) {
        this.date = date;
    }

    @Override
    public int dataType() {
        return TYPE_ZHIHU_DAILY_DATE;
    }
}
