package com.dmytrod.instagramtags.data.api

import com.dmytrod.instagramtags.data.model.Data
import com.dmytrod.instagramtags.data.model.Post
import com.dmytrod.instagramtags.data.model.Tag
import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Dmytro Denysenko on 25.01.18.
 */
interface InstagramService {
    @GET("tags/search")
    fun findTags(@Query("q") query: String): Deferred<Data<List<Tag>>>

    @GET("tags/{tagName}/media/recent")
    fun findPosts(@Path("tagName") tag: String): Deferred<Data<List<Post>>>
}