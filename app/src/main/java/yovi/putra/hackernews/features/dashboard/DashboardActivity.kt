package yovi.putra.hackernews.features.dashboard

import android.os.Bundle
import android.view.View
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
import yovi.putra.hackernews.core.utils.ui.invisible
import yovi.putra.hackernews.core.utils.ui.toast
import yovi.putra.hackernews.core.utils.ui.visible
import yovi.putra.hackernews.data.entity.Story
import yovi.putra.hackernews.features.detail.StoryDetailActivity
import yovi.putra.hackernews.features.favorite.FavoriteViewModel

class DashboardActivity : BaseActivity() {

    private val dashboardVM : DashboardViewModel by viewModel()
    private val favoriteVM : FavoriteViewModel by viewModel()
    private lateinit var adapter: DashboardAdapter


    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        adapter = DashboardAdapter {
            favoriteVM.storys.postValue(it.title)
            StoryDetailActivity.navigate(this, it)
        }

        favoriteVM.storys.observe(this, favoriteObserver)
        dashboardVM.getTopStory(20)?.observe(this, storyObserve)
        dashboardVM.loader.observe(this, loadingObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        list_item.layoutManager = GridLayoutManager(this, 2)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    private var storyObserve = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) {
                    is Story -> {
                        adapter.addItem(it.data)
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
