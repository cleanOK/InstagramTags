package com.dmytrod.instagramtags.data.repository

import com.dmytrod.instagramtags.data.model.Data
import com.dmytrod.instagramtags.data.model.Post
import com.dmytrod.instagramtags.data.model.Tag
import kotlinx.coroutines.experimental.Deferred

/**
 * Created by Dmytro Denysenko on 26.01.18.
 */
interface PostRepository {
    suspend fun findPosts(tag: Tag): Deferred<Data<List<Post>>>
}