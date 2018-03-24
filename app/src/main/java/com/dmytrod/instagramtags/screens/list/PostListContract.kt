package com.dmytrod.instagramtags.screens.list

import android.net.Uri
import com.dmytrod.instagramtags.data.model.Post
import com.dmytrod.instagramtags.data.model.Tag
import com.dmytrod.instagramtags.screens.BaseContract

/**
 * Created by Dmytro Denysenko on 21.01.18.
 */
interface PostListContract {
    interface View {
        fun showPosts(posts: List<Post>)
        fun navigateToAuth(uri: Uri?)
        fun showTags(result: List<Tag>)
        fun showUnknownError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun findTagsByQuery(query: String)
        fun findPostsByTag(tag: Tag)
    }

}