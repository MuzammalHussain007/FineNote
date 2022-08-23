package com.practice.finenote.modals.noteModalResponse

data class GetNoteModal(
    val notes: List<Note>,
    val status: Boolean
)