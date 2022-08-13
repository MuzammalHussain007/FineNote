package com.practice.finenote.viewModal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.repositry.UserRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModal @Inject constructor(private val userRepositry: UserRepositry) : ViewModel() {

    fun registerUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepositry.registerUser(userRequest)
        }
    }


    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepositry.logInUser(userRequest)
        }
    }
}