package yovi.putra.hackernews.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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

class DashboardActivity : BaseActivity() {

    private val dashboardVM : DashboardViewModel by viewModel()
    private lateinit var adapter: DashboardAdapter


    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        adapter = DashboardAdapter {
            StoryDetailActivity.navigate(this, it)
        }
        dashboardVM.getTopStory(20)?.observe(this, storyObserve)
        dashboardVM.loader.observe(this, loadingObserver)
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        list_item.layoutManager = LinearLayoutManager(this)
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
}
