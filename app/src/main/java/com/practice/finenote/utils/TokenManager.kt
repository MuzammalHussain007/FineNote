package com.practice.finenote.utils

import android.content.Context
import com.practice.finenote.utils.Constants.LANGUAGE_FILE
import com.practice.finenote.utils.Constants.LANGUAGE_KEY
import com.practice.finenote.utils.Constants.PREFERENCE_FILE
import com.practice.finenote.utils.Constants.THEME_KEY
import com.practice.finenote.utils.Constants.TOKEN_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor (@ApplicationContext context: Context) {
    private val preference = context.getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE)
    private val Languagepreference = context.getSharedPreferences(LANGUAGE_FILE,Context.MODE_PRIVATE)
    private val editor = preference.edit()
    private val languageEditor = Languagepreference.edit()

    fun saveToken(token:String){
        editor.putString(TOKEN_KEY,token).apply()
    }
    fun getToken() : String? {
        return preference.getString(TOKEN_KEY,"")
    }

    fun saveLanguage(languageType:String){
        languageEditor.putString(LANGUAGE_KEY,languageType).apply()
    }
    fun getLanguage() : String? {
        return Languagepreference.getString(LANGUAGE_KEY,"")
    }
    fun saveTheme(themeType:String){
        languageEditor.putString(THEME_KEY,themeType).apply()
    }
    fun getTheme() : String? {
        return Languagepreference.getString(THEME_KEY,"")
    }

    fun destoryAll(){
        editor.clear()
        editor.commit()
    }
}