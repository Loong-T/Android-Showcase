package in.nerd_is.android_showcase.common.widget;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import in.nerd_is.android_showcase.common.entity.RecyclerData;

/**
 * Created by Xuqiang ZHENG on 2016/12/6.
 */
public abstract class ViewHolder<T extends RecyclerData> extends RecyclerView.ViewHolder {

    public ViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void render(T data);
}
