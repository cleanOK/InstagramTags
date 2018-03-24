package com.dmytrod.instagramtags.screens.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dmytrod.instagramtags.R
import com.dmytrod.instagramtags.data.model.Post
import kotlinx.android.synthetic.main.post_list_item.view.*

/**
 * Created by Dmytro Denysenko on 22.01.18.
 */
class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.post_list_item))

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(posts[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mPostImageView = itemView.post_iv
        private val mAvatarImageView = itemView.avatar_iv
        private val mUsernameTextView = itemView.username_tv

        fun bind(post: Post) {

            Glide.with(itemView)
                    .load(post.images?.standardResolution?.url)
                    .apply(object : RequestOptions() {}
                            .placeholder(android.R.color.darker_gray)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .optionalCenterCrop())
                    .into(mPostImageView)
            Glide.with(itemView)
                    .load(post.user?.profilePicture)
                    .apply(RequestOptions.circleCropTransform())
                    .into(mAvatarImageView)
            mUsernameTextView.text = post.user?.username
        }

    }
}
//Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's
//screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs
//(depending on the input size), use .override(Target.SIZE_ORIGINAL).
//Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.


private fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
