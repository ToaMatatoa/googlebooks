package com.example.googlebooks.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.googlebooks.data.remote.model.ResponseBooks
import com.example.googlebooks.domain.UseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ViewModel(private val useCase: UseCase) : ViewModel() {

    private val liveDataRemote = MutableLiveData<List<ResponseBooks.Book>>()
    val liveDataRemoteProvider: LiveData<List<ResponseBooks.Book>> = liveDataRemote

    private val compositeDisposable = CompositeDisposable()

    private val searchSubject: PublishSubject<String> = PublishSubject.create()
    private val searchSubscription = searchSubject
        .debounce(300, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe {
            searchBooks(it)
        }

    fun getBooks(query: String?) {
        compositeDisposable.add(searchSubscription)
        if (query.isNullOrBlank()) return
        else searchSubject.onNext(query)
    }

    private fun searchBooks(searchStr: String) {
        compositeDisposable.add(
            useCase.getBooksList(searchStr)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveDataRemote.postValue(it)
                }, {
                    Log.getStackTraceString(it)
                })
        )
    }

    fun saveBooksSetting(filter: String) {
        useCase.saveSelectedFilter(filter)
    }

    fun getBooksSetting(): String {
        return useCase.getSelectedFilter()
    }
}