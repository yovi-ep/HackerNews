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
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.core.utils.ui.invisible
import yovi.putra.hackernews.core.utils.ui.toast
import yovi.putra.hackernews.core.utils.ui.visible
import yovi.putra.hackernews.features.detail.StoryDetailActivity

class DashboardActivity : BaseActivity() {

    private val dashboardVM : DashboardViewModel by viewModel()
    private lateinit var adapter: DashboardAdapter


    override fun setupLayoutId(): Int = R.layout.activity_main

    override fun setupData(savedInstanceState: Bundle?) {
        adapter = DashboardAdapter {
            StoryDetailActivity.navigate(this, it)
        }
        dashboardVM.getTopStory()?.observe(this, storyObserve)
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
                    //is MovieListResponse -> { adapter.setItem(it.data.results) }
                }
            }
            is ResultState.Error -> {
                toast(errorMessage(contextView, it.error))
            }
        }
    }

    override fun onShowLoader() {
        pb_loader.visible()
    }

    override fun onHideLoader() {
        pb_loader.invisible()
    }
}
