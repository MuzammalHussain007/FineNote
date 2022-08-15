package com.practice.finenote.responses.getNote

data class Note(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val description: String,
    val title: String,
    val updatedAt: String,
    val userid: String
)