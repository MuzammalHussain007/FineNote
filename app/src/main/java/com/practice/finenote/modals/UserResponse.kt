package com.practice.finenote.modals

data class UserResponse(
    val authenticationToken: String,
    val user: User
)