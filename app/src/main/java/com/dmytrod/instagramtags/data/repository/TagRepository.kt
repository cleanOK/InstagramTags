package com.dmytrod.instagramtags.data.repository

import com.dmytrod.instagramtags.data.model.Data
import com.dmytrod.instagramtags.data.model.Tag
import kotlinx.coroutines.experimental.Deferred

interface TagRepository {
    suspend fun findTags(query: String): Deferred<Data<List<Tag>>>
}