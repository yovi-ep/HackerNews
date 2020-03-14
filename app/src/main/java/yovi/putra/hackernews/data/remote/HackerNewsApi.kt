package yovi.putra.hackernews.data.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import yovi.putra.hackernews.data.entity.Comment
import yovi.putra.hackernews.data.entity.Story

interface HackerNewsApi {
    @GET("topstories.json?print=pretty")
    fun getTopStory() : Observable<MutableList<String>>

    @GET("item/{id}.json?print=pretty")
    fun getStory(@Path("id") id: Int) : Observable<Story>

    @GET("item/{id}.json?print=pretty")
    fun getComment(@Path("id") id: Int) : Observable<Comment>
}