package com.example.googlebooks.data.local

import android.content.SharedPreferences
import com.example.googlebooks.Constants

class SharedPreferences(private val prefs: SharedPreferences) {

    fun saveSelectedFilter(filter: String) {
        prefs.edit().putString(SETTING_PREF_KEY, filter).apply()
    }

    fun getSelectedFilter(): String {
        return prefs.getString(SETTING_PREF_KEY, Constants.CHECK_ALL) ?: Constants.CHECK_ALL
    }

    fun saveEnteredText(text: String) {
        prefs.edit().putString(TEXT_PREF_KEY, text).apply()
    }

    fun getEnteredText(): String {
        return prefs.getString(TEXT_PREF_KEY, "") ?: ""
    }

    companion object {
        private const val SETTING_PREF_KEY = "SETTING_PREF_KEY"
        private const val TEXT_PREF_KEY = "TEXT_PREF_KEY"
    }
}