package com.practice.finenote.viewModal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.repositry.NoteRepositry
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.noteResponse.NoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModal @Inject constructor(private val noteRepositry: NoteRepositry) : ViewModel() {
    val noteLiveData get() = noteRepositry.noteResponseLiveData
    val noteSuccessStatus get() = noteRepositry.noteSuccess

    fun getNotesList() {
        viewModelScope.launch {
            noteRepositry.getNoteList()
        }
    }

    fun addNote(note : NoteResponse){
        viewModelScope.launch {
            noteRepositry.addNotes(note)
        }
    }
    fun updateNote(noteId : String ,note : NoteResponse){
        viewModelScope.launch {
            noteRepositry.updateNote(noteId,note)
        }
    }
    fun deleteNote(noteId : String){
        viewModelScope.launch {
            noteRepositry.deleteNote(noteId)
        }
    }

}