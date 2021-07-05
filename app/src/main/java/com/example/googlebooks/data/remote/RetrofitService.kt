package com.example.googlebooks.data.remote

import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface RetrofitService {

    @GET("books/v1/volumes")
    fun getBooks(@Query(encoded=true, value = "q") searchStr: String?): Observable<ResponseBooks>
}