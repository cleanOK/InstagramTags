package com.dmytrod.instagramtags

import android.app.Application
import android.content.Context
import com.dmytrod.instagramtags.data.api.InstagramService
import com.dmytrod.instagramtags.data.repository.*
import com.dmytrod.instagramtags.repository.SharedPrefAuthRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.experimental.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by Dmytro Denysenko on 25.01.18.
 */
class App : Application() {
    private lateinit var instagramService: InstagramService
    private lateinit var authRepository: AuthRepository
    private lateinit var tagRepository: ApiTagRepository
    private lateinit var postRepository: PostRepository

    override fun onCreate() {
        super.onCreate()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val headerInterceptor = Interceptor {
            val original = it.request()
            val accessToken = getAuthRepository().restoreToken()
            if (accessToken != null) {
                val originalHttpUrl = original.url()
                val url = originalHttpUrl.newBuilder()
                        .addQueryParameter("access_token", accessToken)
                        .build()
                val requestBuilder = original.newBuilder()
                        .url(url)
                val request = requestBuilder.build()
                it.proceed(request)
            } else {
                it.proceed(original)
            }
        }
        val okHttpBuilder = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(headerInterceptor)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpBuilder.build())
                .build()
        instagramService = retrofit.create(InstagramService::class.java)
    }

    //TODO use Dagger2 to inject dependencies
    fun getInstagramService() = instagramService

    fun getAuthRepository(): AuthRepository {
        if (!this::authRepository.isInitialized) {
            authRepository = SharedPrefAuthRepository(getSharedPreferences(getString(R.string.shared_pref_file), Context.MODE_PRIVATE), getString(R.string.scheme))
        }
        return authRepository
    }

    fun getTagRepository(): TagRepository {
        if (!this::tagRepository.isInitialized) {
            tagRepository = ApiTagRepository(getInstagramService())
        }
        return tagRepository
    }

    fun getPostRepository(): PostRepository {
        if (!this::postRepository.isInitialized) {
            postRepository = ApiPostRepository(getInstagramService())
        }
        return postRepository
    }


}