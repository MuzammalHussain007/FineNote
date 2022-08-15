package com.practice.finenote.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.api.UserAPI
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.utils.Constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepositry @Inject constructor(private val noteAPI: NoteAPI) {

    private val _noteResponseMuteableLivedata = MutableLiveData<ErrorHandling<GetNoteResponse>>()
    val noteResponseLiveData : LiveData<ErrorHandling<GetNoteResponse>>
    get() =_noteResponseMuteableLivedata

}