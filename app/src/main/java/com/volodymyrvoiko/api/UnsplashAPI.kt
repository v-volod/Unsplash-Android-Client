package com.volodymyrvoiko.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.volodymyrvoiko.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface UnsplashAPI {
    @GET("/photos")
    suspend fun getPhotos(): List<Photo>
}

fun unsplashRetrofitAPI(): UnsplashAPI = Retrofit.Builder()
    .baseUrl(BuildConfig.UNSPLASH_API_BASE_URL)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
        )
    )
    .client(
        OkHttpClient.Builder()
            .addNetworkInterceptor(UnsplashAuthorizationInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                }
            }
            .build()
    )
    .build()
    .create(UnsplashAPI::class.java)

private object UnsplashAuthorizationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                name = "Authorization",
                value = "Client-ID ${BuildConfig.UNSPLASH_API_ACCESS_KEY}"
            )
            .build()
    )
}