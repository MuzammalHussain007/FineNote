package com.practice.finenote.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.api.UserAPI
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.utils.Constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepositry @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseMuteableLivedata = MutableLiveData<ErrorHandling<UserResponse>>()
    val userResponseLiveData : LiveData<ErrorHandling<UserResponse>>
    get() =_userResponseMuteableLivedata

    suspend fun registerUser(userRequest: UserRequest){
        _userResponseMuteableLivedata.postValue(ErrorHandling.Loading())
        val response = userAPI.signup(userRequest)
        handleUserResponse(response)

    }

    private fun handleUserResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _userResponseMuteableLivedata.postValue(ErrorHandling.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorBody = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseMuteableLivedata.postValue(ErrorHandling.Error(errorBody.getString("message")))

        } else {
            _userResponseMuteableLivedata.postValue(ErrorHandling.Error("Some Thing Went Wrong"))
        }
    }

    suspend fun logInUser(userRequest: UserRequest){
        val response = userAPI.signin(userRequest)
        Log.d(TAG,""+response.body().toString())
        handleUserResponse(response)
    }
}