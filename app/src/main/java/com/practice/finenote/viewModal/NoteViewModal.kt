package com.practice.finenote.viewModal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.repositry.NoteRepositry
import com.practice.finenote.responses.getNote.GetNoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModal @Inject constructor(private val noteRepositry: NoteRepositry) : ViewModel() {
        val noteLiveData get() = noteRepositry.noteResponseLiveData

    fun getNotesList(){
        viewModelScope.launch {
            noteRepositry.getNoteList()
        }
    }

}