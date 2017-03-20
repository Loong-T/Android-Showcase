package in.nerd_is.android_showcase.zhihu_daily_list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import in.nerd_is.android_showcase.R;
import in.nerd_is.android_showcase.common.BaseFragment;
import in.nerd_is.android_showcase.main.MainActivity;
import in.nerd_is.android_showcase.widget.DividerItemDecoration;
import in.nerd_is.recycler_simplification.LoadMoreRecyclerAdapter;

/**
 * @author Xuqiang ZHENG on 2016/10/23.
 */
public class ZhihuDailyListFragment extends BaseFragment
        implements ZhihuDailyListContract.View {

    @Inject
    ZhihuDailyListPresenter presenter;

    private SwipeRefreshLayout swipeRefreshLayout;
    private LoadMoreRecyclerAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((MainActivity) context).mainComponent.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zhihu_daily_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout = find(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(presenter::loadLatestStories);

        RecyclerView recyclerView = find(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration divider = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL, R.drawable.divider_8dp_height);
        divider.setShowFirstDivider(true);
        divider.setShowLastDivider(true);
        recyclerView.addItemDecoration(divider);

        adapter = new LoadMoreRecyclerAdapter(
                new ZhihuDailyTypeFactory(),
                R.layout.zhihu_daily_list_footer,
                data -> presenter.loadMoreStories(data)
        );
        recyclerView.setAdapter(adapter);

        presenter.loadLatestStories();
    }

    @Override @Inject
    public void setupPresenter() {
        presenter.setView(this);
    }

    @Override
    public void refreshing(boolean refreshing) {
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(refreshing));
    }

    @Override
    public void showLatestStories(List<?> data) {
        adapter.swap(data);
        adapter.startEndlessLoadMore();
    }

    @Override
    public void appendStories(List<?> data) {
        adapter.append(data);
        adapter.setLoading(false);
    }
}
