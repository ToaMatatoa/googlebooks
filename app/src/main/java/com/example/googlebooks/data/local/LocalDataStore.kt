package com.example.googlebooks.data.local

class LocalDataStore(private val sharedPreferences: SharedPreferences) {

    fun saveBooksSetting(setting: String) {
            sharedPreferences.saveBooksSetting(setting)
    }
}