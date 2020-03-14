package yovi.putra.hackernews.core.utils.ui

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by yovi.putra
 *    on 09/Jan/2019 19:09
 * Company SIEMO - PT. Multipolar Technology, Tbk
 */
abstract class PaginationScrollListener (var layoutManager: LinearLayoutManager)
            : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading() && !isLastPage()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }
    abstract fun isLastPage(): Boolean

    abstract fun isLoading(): Boolean

    abstract fun loadMoreItems()
}