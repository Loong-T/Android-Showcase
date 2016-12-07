package in.nerd_is.android_showcase.zhihu_daily.entity;

import java.util.List;

import in.nerd_is.android_showcase.common.entity.RecyclerData;

/**
 * Created by loong on 2016/10/7.
 */
public class Story implements RecyclerData {

    public long id;
    public int type;
    public String title;
    public String image;
    public List<String> images;

    @Override
    public int dataType() {
        return TYPE_ZHIHU_DAILY_ITEM;
    }
}
