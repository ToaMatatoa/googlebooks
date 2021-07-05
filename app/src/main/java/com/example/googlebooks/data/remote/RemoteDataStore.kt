package com.example.googlebooks.data.remote

import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Single

class RemoteDataStore {

    private val retrofitService
            by lazy { RetrofitClient.booksService() }

    fun getBooksList(): Single<List<ResponseBooks.Items>> {
        return retrofitService.getBooks() .map { it.items }
    }
}