package com.example.googlebooks.data.remote

import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Single
import retrofit2.http.GET

interface RetrofitService {

    @GET("books/v1/volumes?q=search+terms")
    fun getBooks(): Single<ResponseBooks>
}