package yovi.putra.hackernews.features.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.hackernews.core.base.BaseViewModel
import yovi.putra.hackernews.core.utils.state.LoaderState
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.data.repository.HackerNewsRepository

class DashboardViewModel(private val repo: HackerNewsRepository) : BaseViewModel() {
    private val itemCount = 15
    private var startItem = 0
    private var endItem = itemCount

    private var storys = MutableLiveData<ResultState>()
    private var storysId = mutableListOf<Int>()

    private fun getTopStoryId() {
        repo.getTopStory()
            .doOnTerminate { getStory(1) }
            .subscribe(
                { storysId.addAll(it) },
                { storys.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }

    private fun getPaging(page: Int) : MutableList<Int> {
        val result = mutableListOf<Int>()
        startItem = (page - 1) * itemCount
        endItem = page * itemCount
        if (endItem > storysId.size) endItem = storysId.size

        for (idx in startItem until endItem) {
            Log.e("asd", idx.toString())
            result.add(storysId[idx])
        }
        return result
    }

    private fun getStory(page: Int) {
        repo.getStory(getPaging(page))
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { storys.postValue(ResultState.Success(it)) },
                { storys.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }

    fun getTopStory(page: Int): LiveData<ResultState>? {
        if (storysId.isEmpty()) getTopStoryId()
        getStory(page)
        return storys
    }
}