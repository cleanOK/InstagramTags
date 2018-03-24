package com.dmytrod.instagramtags.screens.list

import android.net.Uri
import android.util.Log
import com.dmytrod.instagramtags.data.model.Tag
import com.dmytrod.instagramtags.data.repository.AuthRepository
import com.dmytrod.instagramtags.data.repository.PostRepository
import com.dmytrod.instagramtags.data.repository.TagRepository
import com.dmytrod.instagramtags.screens.BasePresenter
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import retrofit2.HttpException

/**
 * Created by Dmytro Denysenko on 22.01.18.
 */

class PostListPresenter(private val tagRepository: TagRepository,
                        private val authRepository: AuthRepository,
                        private val postRepository: PostRepository)
    : BasePresenter<PostListContract.View>(), PostListContract.Presenter {

    private val logTag = "PostListPresenter"
    private lateinit var findTagsJob: Job
    private lateinit var findPostsJob: Job

    override fun findTagsByQuery(query: String) {
        if (this::findTagsJob.isInitialized) {
            findTagsJob.cancel()
        }
        findTagsJob = launch(UI) {
            try {
                val deferred = tagRepository.findTags(query)
                val result = deferred.await().data
                result?.let { getView()?.showTags(it) }
            } catch (e: HttpException) {
                Log.e(logTag, "findTagsByQuery()", e)
                handleException(e)
            }
        }
    }

    override fun findPostsByTag(tag: Tag) {
        if (this::findPostsJob.isInitialized) {
            findPostsJob.cancel()
        }
        findPostsJob = launch(UI) {
            try {
                val deferred = postRepository.findPosts(tag)
                val result = deferred.await().data
                result?.let { getView()?.showPosts(result) }
            } catch (e: HttpException) {
                Log.e(logTag, "findPostsByTag()", e)
                handleException(e)
            }
        }
    }

    override fun destroy() {
        if (this::findTagsJob.isInitialized) {
            findTagsJob.cancel()
        }
        if (this::findPostsJob.isInitialized) {
            findPostsJob.cancel()
        }
        super.destroy()
    }

    private fun handleException(e: HttpException) {
        if (e.code() == 400) {
            //TODO move unauthorized logic out this presenter to response interceptor
            authRepository.storeToken(null)
            getView()?.navigateToAuth(Uri.parse(authRepository.getAuthUrl()))
        } else {
            getView()?.showUnknownError()
        }
    }
}
