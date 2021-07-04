package com.example.googlebooks.domain

import com.example.googlebooks.data.RemoteDataStore
import com.example.googlebooks.data.model.ResponseBooks
import io.reactivex.Single

class Repository(private val remoteDataStore: RemoteDataStore) {

    fun getBooksList(): Single<List<ResponseBooks.Items>> {
        return remoteDataStore.getBooksList()
    }
}