package yovi.putra.hackernews.data.repository

import io.reactivex.Observable
import yovi.putra.hackernews.core.utils.thread.RxUtils
import yovi.putra.hackernews.data.entity.Comment
import yovi.putra.hackernews.data.entity.Story
import yovi.putra.hackernews.data.remote.HackerNewsApi

class HackerNewsRepository(private val api: HackerNewsApi) {

    fun getTopStory() : Observable<MutableList<String>> = api.getTopStory().compose(RxUtils.applyObservableAsync())

    fun getStory(id: Int) : Observable<Story> = api.getStory(id).compose(RxUtils.applyObservableAsync())

    fun getComment(id: Int) : Observable<Comment> = api.getComment(id).compose(RxUtils.applyObservableAsync())

}