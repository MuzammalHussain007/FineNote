package com.practice.finenote.responses.deleteNote

data class DeleteNoteResponse(
    val message: String,
    val note: Note,
    val status: Boolean
)