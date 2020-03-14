package yovi.putra.hackernews.data.repository

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import yovi.putra.hackernews.core.utils.thread.RxUtils
import yovi.putra.hackernews.data.entity.Comment
import yovi.putra.hackernews.data.entity.Story
import yovi.putra.hackernews.data.remote.HackerNewsApi

class HackerNewsRepository(private val api: HackerNewsApi) {
    fun getComment(id: Int) : Observable<Comment> = api.getComment(id).compose(RxUtils.applyObservableAsync())

    fun getTopStory() : Observable<MutableList<Int>> = api.getTopStory().compose(RxUtils.applyObservableAsync())

    fun getStory(storyId: MutableList<Int>) : Observable<Story> =
        Observable.fromIterable(storyId)
            .subscribeOn(Schedulers.io())
            .flatMap {
                api.getStory(it)
            }
}