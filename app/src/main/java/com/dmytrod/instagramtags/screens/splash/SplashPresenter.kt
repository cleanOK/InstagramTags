package com.dmytrod.instagramtags.screens.splash

import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.dmytrod.instagramtags.data.repository.AuthRepository
import com.dmytrod.instagramtags.screens.BasePresenter

/**
 * Created by Dmytro Denysenko on 23.01.18.
 */
class SplashPresenter(private val authRepository: AuthRepository) : BasePresenter<SplashContract.View>(), SplashContract.Presenter {
    private val logTag = "SplashPresenter"

    override fun checkToken() {
        if (authRepository.restoreToken() == null) {
            getView()?.navigateToAuth(Uri.parse(authRepository.getAuthUrl()))
        } else {
            getView()?.navigateToList()
        }
    }

    override fun checkIntentData(intentData: Uri) {
        if (authRepository.getScheme() == intentData.scheme) {
            val accessToken = intentData.fragment.substringAfter('=', "")
            Log.d(logTag, "intentData = $intentData")
            if (TextUtils.isEmpty(accessToken)) {
                Log.w(logTag, "no access token")
                //TODO handle case
            } else {
                authRepository.storeToken(accessToken)
                getView()?.navigateToList()
            }
        }
    }
}