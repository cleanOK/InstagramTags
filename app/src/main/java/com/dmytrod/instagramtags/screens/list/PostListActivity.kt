package com.dmytrod.instagramtags.screens.list

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.AutoCompleteTextView
import com.dmytrod.instagramtags.App
import com.dmytrod.instagramtags.R
import com.dmytrod.instagramtags.data.model.Post
import com.dmytrod.instagramtags.data.model.Tag
import kotlinx.android.synthetic.main.activity_post_list.*


/**
 * Created by Dmytro Denysenko on 21.01.18.
 */
class PostListActivity : AppCompatActivity(), PostListContract.View {

    private val mPosts = mutableListOf<Post>()
    private val mTags = mutableListOf<Tag>()
    private lateinit var tagAdapter: TagAutocompleteAdapter
    private lateinit var mPresenter: PostListPresenter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mTagSearchView: AutoCompleteTextView
    private lateinit var mRoot: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_list)
        val app = application as App
        mPresenter = PostListPresenter(app.getTagRepository(), app.getAuthRepository(), app.getPostRepository())
        mPresenter.setView(this)
        initViews()
    }

    override fun onDestroy() {
        mPresenter.destroy()
        super.onDestroy()
    }

    override fun showPosts(posts: List<Post>) {
        mPosts.clear()
        mPosts.addAll(posts)
        mRecyclerView.adapter.notifyDataSetChanged()
    }

    override fun showTags(result: List<Tag>) {
        mTags.clear()
        mTags.addAll(result)
        mTagSearchView.showDropDown()
        if (mTags.size > 0) {
            tagAdapter.notifyDataSetChanged()
        } else {
            tagAdapter.notifyDataSetInvalidated()
        }
    }

    override fun navigateToAuth(uri: Uri?) {
        Snackbar.make(mRoot, R.string.unauthorized_error_message, Snackbar.LENGTH_SHORT)
                .setAction(R.string.login,
                        {
                            val browserIntent = Intent(Intent.ACTION_VIEW)
                                    .setData(uri)
                            startActivity(browserIntent)
                            finish()
                        })
                .show()
    }

    override fun showUnknownError() {
        Snackbar.make(mRoot, R.string.unknown_error_message, Snackbar.LENGTH_SHORT).show()
    }

    private fun initViews() {
        mRoot = root
        tagAdapter = TagAutocompleteAdapter(this, mTags, { query -> mPresenter.findTagsByQuery(query) })
        mTagSearchView = tag_view
        mTagSearchView.threshold = 2
        mTagSearchView.setAdapter(tagAdapter)
        mTagSearchView.setOnItemClickListener { adapterView, _, position, _ -> mPresenter.findPostsByTag(adapterView.getItemAtPosition(position) as Tag) }
        mRecyclerView = recycler_view
        mRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = PostAdapter(mPosts)
        mRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, PostListActivity::class.java)
    }

}
