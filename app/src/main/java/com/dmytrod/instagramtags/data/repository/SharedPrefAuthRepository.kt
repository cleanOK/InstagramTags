package com.dmytrod.instagramtags.repository

import android.content.SharedPreferences
import com.dmytrod.instagramtags.data.repository.AuthRepository

/**
 * Created by Dmytro Denysenko on 25.01.18.
 * For testing purposes only.
 * Shouldn't be used in prod, because not secure enough
 */
class SharedPrefAuthRepository(private val sharedPreferences: SharedPreferences, private val scheme: String) : AuthRepository {
    //TODO store in keystore
    private val clientId = "25bd0cae827a4aa4a9d33795d87e7446"

    private val keyToken = "keyToken"
    private val authUrl = "https://api.instagram.com/oauth/authorize/?client_id=$clientId&redirect_uri=$scheme://dmytrod.com/auth&response_type=token&scope=public_content"

    override fun storeToken(accessToken: String?) =
            sharedPreferences.edit().putString(keyToken, accessToken).apply()

    override fun restoreToken(): String? = sharedPreferences.getString(keyToken, null)

    override fun getAuthUrl() = authUrl

    override fun getScheme() = scheme
}

