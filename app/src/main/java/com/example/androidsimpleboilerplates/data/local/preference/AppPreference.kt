package com.example.androidsimpleboilerplates.data.local.preference

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppPreference @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson? = null
): PreferenceHelper {

    override fun saveUserName(name: String) {
        TODO("Not yet implemented")
    }

    override fun getUserName(key: String): String? {
        return null
    }

}