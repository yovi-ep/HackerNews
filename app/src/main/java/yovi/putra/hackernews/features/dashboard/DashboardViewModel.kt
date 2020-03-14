package yovi.putra.hackernews.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.hackernews.core.base.BaseViewModel
import yovi.putra.hackernews.core.utils.state.LoaderState
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.data.repository.HackerNewsRepository

class DashboardViewModel(private val repo: HackerNewsRepository) : BaseViewModel() {
    private var storys: MutableLiveData<ResultState>? = null

    fun getTopStory(page: Int): LiveData<ResultState>? {
        storys ?: run { storys = MutableLiveData() }
        repo.getTopStory()
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .map {
                val paging = mutableListOf<Int>()
                for (idx in 0 until page){
                    paging.add(it[idx])
                }
                paging
            }
            .flatMap { repo.getStory(it) }
            .subscribe(
                { storys?.postValue(ResultState.Success(it)) },
                { storys?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
        return storys
    }
}