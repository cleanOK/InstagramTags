package com.dmytrod.instagramtags.data.repository

import com.dmytrod.instagramtags.data.api.InstagramService
import com.dmytrod.instagramtags.data.model.Tag

/**
 * Created by Dmytro Denysenko on 26.01.18.
 */
class ApiPostRepository(private val instagramService: InstagramService) : PostRepository {

    override suspend fun findPosts(tag: Tag) = instagramService.findPosts(tag.name)

}