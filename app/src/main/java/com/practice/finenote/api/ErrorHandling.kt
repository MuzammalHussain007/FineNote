package com.practice.finenote.api

sealed class ErrorHandling<T>(private val list: T? =null , private val errorMsg : String ?=null) {

    data class Success<T>( val data: T) : ErrorHandling<T>(list = data)
    data class Error<T>(  val error: String) : ErrorHandling<T>(errorMsg = error)
    class  Loading<T>(): ErrorHandling<T>()
}