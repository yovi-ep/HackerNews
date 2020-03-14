package yovi.putra.hackernews.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_story_detail.*
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.viewmodel.ext.android.viewModel
import yovi.putra.hackernews.R
import yovi.putra.hackernews.core.base.BaseToolbarActivity
import yovi.putra.hackernews.core.utils.network.NetworkThrowable.errorMessage
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.core.utils.toDate
import yovi.putra.hackernews.core.utils.ui.toast
import yovi.putra.hackernews.data.entity.Comment
import yovi.putra.hackernews.data.entity.Story

class StoryDetailActivity : BaseToolbarActivity() {
    private val storyDetailVM : StoryDetailViewModel by viewModel()
    private lateinit var data: Story
    private lateinit var adapter: CommentAdapter

    companion object {
        private const val INTENT_DATA = "STORY"

        fun navigate(context: Context, data: Story) {
            val intent = Intent(context, StoryDetailActivity::class.java).apply {
                putExtra(INTENT_DATA, data)
            }
            context.startActivity(intent)
        }
    }

    override fun setButtonBack(): Boolean = true

    override fun setupLayoutId(): Int = R.layout.activity_story_detail

    override fun setupData(savedInstanceState: Bundle?) {
        data = intent.getParcelableExtra(INTENT_DATA) ?: Story()
        adapter = CommentAdapter()

        data.kids?.let {
            storyDetailVM.getComments(it)?.observe(this, commentObserve)
        }
    }

    override fun setupUI(savedInstanceState: Bundle?) {
        setToolbar(R.id.toolbar)
        tv_title.text = data.title
        tv_author.text = data.by
        tv_date.text = data.time?.toDate()
        tv_description.text = data.url

        list_item.layoutManager = LinearLayoutManager(this)
        list_item.overScrollMode = View.OVER_SCROLL_NEVER
        list_item.adapter = adapter
    }

    private var commentObserve = Observer<ResultState> {
        when (it) {
            is ResultState.Success<*> -> {
                when (it.data) {
                    is Comment -> {
                        adapter.addItem(it.data)
                    }
                }
            }
            is ResultState.Error -> {
                toast(errorMessage(contextView, it.error))
            }
        }
    }
}
