package yovi.putra.hackernews.features.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.hackernews.core.base.BaseViewModel
import yovi.putra.hackernews.core.utils.state.LoaderState
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.data.repository.HackerNewsRepository

class DashboardViewModel(private val repo: HackerNewsRepository) : BaseViewModel() {
    private var listIdStory: MutableLiveData<ResultState>? = null

    fun getTopStory(): LiveData<ResultState>? {
        listIdStory?.let {
            loaderState.postValue(LoaderState.Hide)
        } ?: run {
            setTopStory()
        }
        return listIdStory
    }

    fun setTopStory() {
        listIdStory ?: run { listIdStory = MutableLiveData() }
        repo.getTopStory()
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { listIdStory?.postValue(ResultState.Success(it)) },
                { listIdStory?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
    }
}