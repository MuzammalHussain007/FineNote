package com.practice.finenote.utils

import android.content.Context
import com.practice.finenote.utils.Constants.PREFERENCE_FILE
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
}