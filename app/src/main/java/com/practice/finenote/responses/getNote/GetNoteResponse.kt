package com.practice.finenote.responses.getNote

data class GetNoteResponse(
    val notes: List<Note>,
    val status: Boolean
)