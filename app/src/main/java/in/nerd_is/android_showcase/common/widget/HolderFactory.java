package in.nerd_is.android_showcase.common.widget;

import android.util.SparseArray;
import android.view.LayoutInflater;

import in.nerd_is.android_showcase.ThisApplication;

/**
 * Created by Xuqiang ZHENG on 2017/1/14.
 */
public abstract class HolderFactory {

    private SparseArray<ViewHolder> rulesMap;

    protected LayoutInflater inflater = LayoutInflater.from(ThisApplication.INSTANCE);

    public HolderFactory() {
        rulesMap = new SparseArray<>();
        setupTypeRules(rulesMap);
    }

    public abstract void setupTypeRules(SparseArray<ViewHolder> map);

    public final ViewHolder createViewHolder(int type) {
        final ViewHolder holder = rulesMap.get(type);
        if (holder == null) {
            throw new IllegalArgumentException("Unknown view type: " + type);
        }

        return holder;
    }
}
