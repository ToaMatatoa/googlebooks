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

class ViewModel(private val useCase: UseCase) : ViewModel() {

    private val liveDataRemote = MutableLiveData<List<ResponseBooks.Items>>()
    val liveDataRemoteProvider: LiveData<List<ResponseBooks.Items>> = liveDataRemote

    private val compositeDisposable = CompositeDisposable()

    fun getBooks() {
        compositeDisposable.add(
            useCase.getBooksList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    liveDataRemote.postValue(it)
                }, {
                    Log.getStackTraceString(it)
                })
        )
    }

    fun saveBooksSetting(setting: String) {
        useCase.saveBooksSetting(setting)
    }
}