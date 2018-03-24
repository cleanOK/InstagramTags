package com.dmytrod.instagramtags.data.repository

import com.dmytrod.instagramtags.data.api.InstagramService

/**
 * Created by Dmytro Denysenko on 25.01.18.
 */
class ApiTagRepository(private val instagramService: InstagramService) : TagRepository {
    override suspend fun findTags(query: String) = instagramService.findTags(query)

}