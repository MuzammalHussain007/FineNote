package com.practice.finenote.responses.addNote

data class AddNoteResponse(
    val note: Note,
    val status: Boolean
)