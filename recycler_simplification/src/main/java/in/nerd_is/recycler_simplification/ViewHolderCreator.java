package in.nerd_is.recycler_simplification;

import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * @author Xuqiang ZHENG on 2017/2/25.
 */
public interface ViewHolderCreator {
    ViewHolder create(LayoutInflater inflater, ViewGroup parent);
}
