package yovi.putra.hackernews.core.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by yovi.putra
 * on 14/Mar/2020 09:45
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class BaseActivity : AppCompatActivity(), IBaseView {

    override val contextView: Context
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setupLayoutId())
        setupData(savedInstanceState)
        setupUI(savedInstanceState)
    }

    abstract fun setupLayoutId() : Int

    abstract fun setupData(savedInstanceState: Bundle?)

    abstract fun setupUI(savedInstanceState: Bundle?)

    override fun onShowLoader() {}

    override fun onHideLoader() {}
}