package com.example.googlebooks.data.remote

import com.example.googlebooks.data.remote.model.ResponseBooks.Book
import io.reactivex.Observable
import io.reactivex.Single

class RemoteDataStore {

    private val retrofitService by lazy { RetrofitClient.booksService() }

    fun getBooksList(searchStr: String, filter: String): Observable<List<Book>> {
        return retrofitService.getBooks("$searchStr+$filter").map { it.items }
    }
}