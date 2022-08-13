package com.practice.finenote.repositry

import android.util.Log
import com.practice.finenote.api.UserAPI
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.utils.Constants.TAG
import javax.inject.Inject

class UserRepositry @Inject constructor(private val userAPI: UserAPI) {

    suspend fun registerUser(userRequest: UserRequest){
        val response = userAPI.signup(userRequest)
        Log.d(TAG,""+response.body().toString())
    }

    suspend fun logInUser(userRequest: UserRequest){
        val response = userAPI.signin(userRequest)
        Log.d(TAG,""+response.body().toString())

    }
}