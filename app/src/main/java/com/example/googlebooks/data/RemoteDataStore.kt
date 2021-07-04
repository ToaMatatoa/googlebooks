package com.example.googlebooks.data

import com.example.googlebooks.data.model.ResponseBooks
import io.reactivex.Single

class RemoteDataStore {

    private val retrofitService
            by lazy { RetrofitClient.booksService() }

    fun getBooksList(): Single<List<ResponseBooks.Items>> {
        return retrofitService.getBooks() .map { it.items }
    }
}