package com.practice.finenote.viewModal

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.repositry.NoteRepositry
import com.practice.finenote.repositry.UserRepositry
import com.practice.finenote.responses.getNote.GetNoteResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModal @Inject constructor(private val noteRepositry: NoteRepositry) : ViewModel() {
    val noteResponseLiveData: LiveData<ErrorHandling<List<GetNoteResponse>>>
        get() = noteRepositry.noteResponseLiveData


    fun getNotes() {
        viewModelScope.launch {
            noteRepositry.getNotes()
        }
    }

}