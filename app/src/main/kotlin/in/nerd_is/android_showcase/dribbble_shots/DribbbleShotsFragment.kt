/*
 *    Copyright 2017 Xuqiang ZHENG
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package `in`.nerd_is.android_showcase.dribbble_shots

import `in`.nerd_is.android_showcase.R
import `in`.nerd_is.android_showcase.common.BaseFragment
import `in`.nerd_is.android_showcase.common.lib_support.rx.LifecycleSingleObserver
import `in`.nerd_is.android_showcase.databinding.DribbbleShotsFragmentBinding
import `in`.nerd_is.android_showcase.dribbble.model.Shot
import `in`.nerd_is.android_showcase.dribbble.viewmodel.DaggerViewModelFactory
import `in`.nerd_is.android_showcase.dribbble_shots.viewmodel.ShotsViewModel
import `in`.nerd_is.recycler_simplification.LoadMoreRecyclerAdapter
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * @author Xuqiang ZHENG on 2017/6/14.
 */
class DribbbleShotsFragment : BaseFragment(), LoadMoreRecyclerAdapter.OnLoadMoreListener {

    @Inject lateinit var viewModelFactory: DaggerViewModelFactory
    private lateinit var binding: DribbbleShotsFragmentBinding
    private lateinit var viewModel: ShotsViewModel

    private val recyclerAdapter: LoadMoreRecyclerAdapter = LoadMoreRecyclerAdapter(
            DribbbleShotsTypeFactory(),
            R.layout.load_more_footer,
            this)

    private val refreshShotsObserver = object : LifecycleSingleObserver<List<Shot>>(this) {
        override fun onSubscribe(disposable: Disposable) {
            super.onSubscribe(disposable)
            binding.swipeRefreshLayout.isRefreshing = true
        }

        override fun onError(e: Throwable) {
            showError(e)
            binding.swipeRefreshLayout.isRefreshing = false
        }

        override fun onSuccess(data: List<Shot>) {
            recyclerAdapter.swap(data)
            binding.swipeRefreshLayout.isRefreshing = false
            recyclerAdapter.startEndlessLoadMore()
        }
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
                inflater, R.layout.dribbble_shots_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        binding.recyclerView.layoutManager = layoutManager
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this, viewModelFactory)[ShotsViewModel::class.java]

        binding.recyclerView.adapter = recyclerAdapter

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getShots().subscribe(refreshShotsObserver)
        }

        viewModel.getShots().subscribe(refreshShotsObserver)
    }

    override fun loadMore(originData: MutableList<*>) {
        viewModel.getNextPageShots()
                .subscribe(object : LifecycleSingleObserver<List<Shot>>(this) {
                    override fun onSuccess(data: List<Shot>) {
                        recyclerAdapter.append(data)
                        recyclerAdapter.isLoading = false
                    }

                    override fun onError(e: Throwable) {
                        showError(e)
                        recyclerAdapter.isLoading = false
                    }
                })
    }
}