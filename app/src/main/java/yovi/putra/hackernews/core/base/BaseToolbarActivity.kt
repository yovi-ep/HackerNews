package yovi.putra.hackernews.core.base

import androidx.appcompat.widget.Toolbar

abstract class BaseToolbarActivity : BaseActivity(), IToolbar {

    protected fun setToolbar(idToolbar: Int) {
        val toolbar = findViewById<Toolbar>(idToolbar)
        toolbar.let {
            setSupportActionBar(toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(setButtonBack())
            supportActionBar?.setDisplayShowTitleEnabled(false)

            if (setButtonBack()) {
                toolbar.setNavigationOnClickListener { finish() }
            }
        }
    }

    override fun setButtonBack(): Boolean = false
}
