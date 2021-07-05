package com.example.googlebooks.data.local

import android.content.SharedPreferences
import com.example.googlebooks.Constants

class SharedPreferences( private val prefs: SharedPreferences) {

    fun saveSelectedFilter(filter: String) {
        prefs.edit().putString(SETTING_PREF_KEY, filter).commit()
    }

    fun getSelectedFilter(): String {
        return prefs.getString(SETTING_PREF_KEY, Constants.CHECK_ALL) ?: Constants.CHECK_ALL
    }

    companion object {
        private const val SETTING_PREF_KEY = "SETTING_PREF_KEY"
    }
}