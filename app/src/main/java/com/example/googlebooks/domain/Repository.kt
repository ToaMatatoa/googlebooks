package com.example.googlebooks.domain

import com.example.googlebooks.data.local.LocalDataStore
import com.example.googlebooks.data.remote.RemoteDataStore
import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Single

class Repository(
    private val remoteDataStore: RemoteDataStore,
    private val localDataStore: LocalDataStore
) {

    fun getBooksList(): Single<List<ResponseBooks.Items>> {
        return remoteDataStore.getBooksList()
    }

    fun saveBooksSetting(setting: String) {
        localDataStore.saveBooksSetting(setting)
    }

    fun getBooksSetting(): String {
        return localDataStore.getBooksSetting()
    }
}