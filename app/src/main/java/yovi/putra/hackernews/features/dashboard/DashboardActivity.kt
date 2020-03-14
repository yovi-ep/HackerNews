package yovi.putra.hackernews.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.hackernews.R
import yovi.putra.hackernews.core.base.BaseActivity
import yovi.putra.hackernews.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.hackernews.core.utils.state.LoaderState
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.core.utils.ui.*
import yovi.putra.hackernews.data.entity.Story
import yovi.putra.hackernews.features.detail.StoryDetailActivity
import yovi.putra.hackernews.features.favorite.FavoriteViewModel

class DashboardActivity : BaseActivity() {
    private val dashboardVM : DashboardViewModel by viewModel()
    private val favoriteVM : FavoriteViewModel by viewModel()
    private lateinit var adapter: DashboardAdapter
    private var lastPage = false
    private var page = 0

    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        adapter = DashboardAdapter {
            StoryDetailActivity.navigate(this, it)
            favoriteVM.storys.postValue(it.title)
        }
        favoriteVM.storys.observe(this, favoriteObserver)
        dashboardVM.loader.observe(this, loadingObserver)
        loadData()
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        val layout = GridLayoutManager(this, 2)
        list_item.layoutManager = layout
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
        list_item.addOnScrollListener(object : PaginationScrollListener(layout) {
            override fun isLastPage(): Boolean = lastPage

            override fun isLoading(): Boolean = pb_loader.isVisible()

            override fun loadMoreItems() {
                lastPage = true
                loadData()
            }
        })
    }

    private fun loadData() {
        page++
        dashboardVM.getTopStory(page)?.observe(this, storyObserve)
    }

    private var storyObserve = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) {
                    is Story -> {
                        adapter.addItem(it.data)
                        lastPage = false
                    }
                }
            }
            is ResultState.Error -> {
                toast(errorMessage(contextView, it.error))
            }
        }
    }

    private var loadingObserver = Observer<LoaderState> {
        when (it) {
            is LoaderState.Show -> pb_loader.visible()
            is LoaderState.Hide -> pb_loader.invisible()
        }
    }

    private var favoriteObserver = Observer<String> {
        if (it.isEmpty()) {
            tv_favorite.text = getString(R.string.favorite_not_available)
        } else {
            tv_favorite.text = it
        }
    }
}
