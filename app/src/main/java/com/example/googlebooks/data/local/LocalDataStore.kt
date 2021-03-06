package com.example.googlebooks.data.local

class LocalDataStore(private val sharedPreferences: SharedPreferences) {

    fun saveSelectedFilter(filter: String) {
        sharedPreferences.saveSelectedFilter(filter)
    }

    fun getSelectedFilter(): String {
        return sharedPreferences.getSelectedFilter()
    }

    fun saveEnteredText(text: String) {
        sharedPreferences.saveEnteredText(text)
    }

    fun getEnteredText(): String {
        return sharedPreferences.getEnteredText()
    }
}