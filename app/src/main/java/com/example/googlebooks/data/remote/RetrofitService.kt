package com.example.googlebooks.data.remote

import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("books/v1/volumes")
    fun getBooks(@Query(encoded = true, value = "q") searchStr: String?): Observable<ResponseBooks>
}