package yovi.putra.hackernews.data.repository

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import yovi.putra.hackernews.core.utils.state.ResultState
import yovi.putra.hackernews.core.utils.thread.RxUtils
import yovi.putra.hackernews.data.entity.Comment
import yovi.putra.hackernews.data.entity.Story
import yovi.putra.hackernews.data.remote.HackerNewsApi

class HackerNewsRepository(private val api: HackerNewsApi) {
    private var listIdStory: MutableLiveData<String>? = null

    fun getTopStory() : Observable<MutableList<String>> {
        return api.getTopStory()
            .compose(RxUtils.applyObservableAsync())

    }

    fun getStory(id: Int) : Observable<Story> = api.getStory(id).compose(RxUtils.applyObservableAsync())

    fun getComment(id: Int) : Observable<Comment> = api.getComment(id).compose(RxUtils.applyObservableAsync())

}