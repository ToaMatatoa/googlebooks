package com.example.googlebooks.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BOOKS_URL = "https://www.googleapis.com/"
    private var retrofit: Retrofit? = null
    private val logging = HttpLoggingInterceptor()

    fun booksService(): RetrofitService {

        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(logging)
        }.build()

        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BOOKS_URL)
                .client(okHttpClient.newBuilder().build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        return retrofit!!.create(RetrofitService::class.java)
    }
}