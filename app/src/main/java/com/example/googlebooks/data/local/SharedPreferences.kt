package com.example.googlebooks.data.local

import android.content.SharedPreferences

class SharedPreferences( private val prefs: SharedPreferences) {

    fun saveBooksSetting(setting: String) {
        prefs.edit().putString(SETTING_PREF_KEY, setting).apply()
    }

    fun getBooksSetting(): String {
        return prefs.getString(SETTING_PREF_KEY, null) ?: "terms"
    }

    companion object {
        private const val SETTING_PREF_KEY = "SETTING_PREF_KEY"
    }
}