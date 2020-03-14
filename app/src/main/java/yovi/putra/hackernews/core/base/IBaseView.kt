package yovi.putra.hackernews.core.base

import android.content.Context

/**
 * Created by yovi.putra
 * on 14/Mar/2020 10:55
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
interface IBaseView {

    val contextView: Context

    fun onShowLoader()

    fun onHideLoader()
}
