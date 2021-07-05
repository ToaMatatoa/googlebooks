package com.example.googlebooks.domain

import com.example.googlebooks.data.remote.model.ResponseBooks
import io.reactivex.Single

class UseCase(private val repository: Repository) {

    fun getBooksList(): Single<List<ResponseBooks.Items>> {
        return repository.getBooksList()
    }

    fun saveBooksSetting(setting: String) {
        repository.saveBooksSetting(setting)
    }
}