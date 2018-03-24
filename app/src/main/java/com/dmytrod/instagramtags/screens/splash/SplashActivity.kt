package com.dmytrod.instagramtags.screens.splash

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.dmytrod.instagramtags.App
import com.dmytrod.instagramtags.R
import com.dmytrod.instagramtags.screens.list.PostListActivity


/**
 * Created by Dmytro Denysenko on 23.01.18.
 */
class SplashActivity : AppCompatActivity(), SplashContract.View {

    private lateinit var mPresenter: SplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        mPresenter = SplashPresenter((application as App).getAuthRepository())
        mPresenter.setView(this)
        if (intent != null && intent.data != null) {
            mPresenter.checkIntentData(intent.data)
        } else {
            mPresenter.checkToken()
        }
    }

    override fun navigateToAuth(uri: Uri) {
        //TODO implement via custom WebView or ChromeCustomTabs
        val browserIntent = Intent(Intent.ACTION_VIEW)
                .setData(uri)
        startActivity(browserIntent)
        finish()
    }

    override fun navigateToList() {
        startActivity(PostListActivity.createIntent(this))
        finish()
    }
}