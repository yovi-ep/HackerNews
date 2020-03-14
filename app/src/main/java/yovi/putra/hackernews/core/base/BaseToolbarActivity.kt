package yovi.putra.hackernews.core.base

import androidx.appcompat.widget.Toolbar

abstract class BaseToolbarActivity : BaseActivity(), IToolbar {

    protected fun setToolbar(idToolbar: Int) {
        findViewById<Toolbar>(idToolbar)?.let {
            setSupportActionBar(it)
            supportActionBar?.setDisplayHomeAsUpEnabled(setButtonBack())
            supportActionBar?.setDisplayShowTitleEnabled(false)

            if (setButtonBack()) {
                it.setNavigationOnClickListener { finish() }
            }
        }
    }

    override fun setButtonBack(): Boolean = false
}
