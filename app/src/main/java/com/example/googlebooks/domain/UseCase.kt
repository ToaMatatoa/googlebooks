package com.example.googlebooks.domain

import com.example.googlebooks.data.remote.model.ResponseBooks.Book
import io.reactivex.Observable

class UseCase(private val repository: Repository) {

    fun getBooksList(searchStr: String): Observable<List<Book>> {
        return repository.getBooksList(searchStr)
    }

    fun saveSelectedFilter(filter: String) {
        repository.saveSelectedFilter(filter)
    }

    fun getSelectedFilter(): String {
        return repository.getSelectedFilter()
    }
}