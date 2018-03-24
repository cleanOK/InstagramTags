package com.dmytrod.instagramtags.data.repository

interface AuthRepository {

    fun storeToken(accessToken: String?)
    fun restoreToken(): String?
    fun getAuthUrl(): String
    fun getScheme(): String
}