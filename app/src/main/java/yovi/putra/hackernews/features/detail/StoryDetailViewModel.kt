package yovi.putra.hackernews.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo
import yovi.putra.hackernews.core.base.BaseViewModel
import yovi.putra.hackernews.core.utils.state.LoaderState
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.data.repository.HackerNewsRepository

class StoryDetailViewModel(private val repo: HackerNewsRepository) : BaseViewModel() {
    private var comment: MutableLiveData<ResultState>? = null

    fun getComments(commentId: MutableList<Int>): LiveData<ResultState>? {
        comment ?: run { comment = MutableLiveData() }

        repo.getComments(commentId)
            .doOnSubscribe { loaderState.postValue(LoaderState.Show) }
            .doOnTerminate { loaderState.postValue(LoaderState.Hide) }
            .subscribe(
                { comment?.postValue(ResultState.Success(it)) },
                { comment?.postValue(ResultState.Error(it)) }
            )
            .addTo(subscriber)
        return comment
    }
}