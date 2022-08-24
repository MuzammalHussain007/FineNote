package com.practice.finenote.utils

import android.content.Context
import com.practice.finenote.utils.Constants.LANGUAGE_KEY
import com.practice.finenote.utils.Constants.PREFERENCE_FILE
import com.practice.finenote.utils.Constants.THEME_KEY
import com.practice.finenote.utils.Constants.TOKEN_KEY
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor (@ApplicationContext context: Context) {
    private val preference = context.getSharedPreferences(PREFERENCE_FILE,Context.MODE_PRIVATE)
    private val editor = preference.edit()

    fun saveToken(token:String){
        editor.putString(TOKEN_KEY,token).apply()
    }
    fun getToken() : String? {
        return preference.getString(TOKEN_KEY,"")
    }

    fun saveLanguage(languageType:String){
        editor.putString(LANGUAGE_KEY,languageType).apply()
    }
    fun getLanguage() : String? {
        return preference.getString(LANGUAGE_KEY,"")
    }
    fun saveTheme(themeType:String){
        editor.putString(THEME_KEY,themeType).apply()
    }
    fun getTheme() : String? {
        return preference.getString(THEME_KEY,"")
    }

    fun destoryAll(){
        editor.clear()
        editor.commit()
    }
}