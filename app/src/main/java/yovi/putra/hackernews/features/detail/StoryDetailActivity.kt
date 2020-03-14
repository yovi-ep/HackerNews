package yovi.putra.hackernews.features.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import yovi.putra.hackernews.R
import yovi.putra.hackernews.core.base.BaseToolbarActivity
import yovi.putra.hackernews.data.entity.Story

class StoryDetailActivity : BaseToolbarActivity() {

    private lateinit var data: Story

    companion object {
        private const val INTENT_DATA = "STORY"

        fun navigate(context: Context, data: Story) {
            val intent = Intent(context, StoryDetailActivity::class.java).apply {
                putExtra(INTENT_DATA, data)
            }
            context.startActivity(intent)
        }
    }

    override fun setupLayoutId(): Int = R.layout.activity_story_detail

    override fun setupData(savedInstanceState: Bundle?) {
        data = intent.getParcelableExtra(INTENT_DATA) ?: Story()
    }

    override fun setupUI(savedInstanceState: Bundle?) {
    }
}
