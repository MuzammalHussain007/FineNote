package com.practice.finenote.responses.updateNote

import com.practice.finenote.responses.updateNote.Note

data class UpdateNoteResponse(
    val message: String,
    val note: Note,
    val status: Boolean
)