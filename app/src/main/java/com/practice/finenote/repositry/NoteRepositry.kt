package com.practice.finenote.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.getNote.Note
import com.practice.finenote.utils.Constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepositry @Inject constructor(private val noteAPI: NoteAPI) {

    private val _noteResponseMuteableLivedata = MutableLiveData<ErrorHandling<List<Note>>>()
    val noteResponseLiveData: LiveData<ErrorHandling<List<Note>>>
        get() = _noteResponseMuteableLivedata


    suspend fun getNoteList() {
        _noteResponseMuteableLivedata.postValue(ErrorHandling.Loading())
        val response = noteAPI.getNote()
        //   Log.d(TAG,"getNoteList()-->"+response.body().toString())
        handleNoteResponse(response)

    }

    private fun handleNoteResponse(response: Response<GetNoteResponse>) {
        if (response.isSuccessful && response.body() != null) {
            _noteResponseMuteableLivedata.postValue(ErrorHandling.Success(response.body()!!.notes))
        } else if (response.errorBody() != null) {
            val errorBody = JSONObject(response.errorBody()!!.charStream().readText())
            _noteResponseMuteableLivedata.postValue(ErrorHandling.Error(errorBody.getString("message")))

        } else {
            _noteResponseMuteableLivedata.postValue(ErrorHandling.Error("Some Thing Went Wrong"))
        }
    }


}