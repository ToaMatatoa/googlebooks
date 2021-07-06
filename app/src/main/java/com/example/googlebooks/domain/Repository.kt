package com.example.googlebooks.domain

import com.example.googlebooks.data.local.LocalDataStore
import com.example.googlebooks.data.remote.RemoteDataStore
import com.example.googlebooks.data.remote.model.ResponseBooks.Book
import io.reactivex.Observable

class Repository(
    private val remoteDataStore: RemoteDataStore,
    private val localDataStore: LocalDataStore
) {

    fun getBooksList(searchStr: String): Observable<List<Book>> {
        return remoteDataStore.getBooksList(searchStr, localDataStore.getSelectedFilter())
    }

    fun saveSelectedFilter(filter: String) {
        localDataStore.saveSelectedFilter(filter)
    }

    fun getSelectedFilter(): String {
        return localDataStore.getSelectedFilter()
    }

    fun saveEnteredText(text: String) {
        localDataStore.saveEnteredText(text)
    }

    fun getEnteredText(): String {
        return localDataStore.getEnteredText()
    }
}