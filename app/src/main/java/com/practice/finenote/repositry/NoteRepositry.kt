package com.practice.finenote.repositry

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.api.NoteAPI
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.responses.addNote.AddNoteResponse
import com.practice.finenote.responses.deleteNote.DeleteNoteResponse
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.getNote.Note
import com.practice.finenote.responses.noteResponse.NoteResponse
import com.practice.finenote.responses.updateNote.UpdateNoteResponse
import com.practice.finenote.utils.Constants.TAG
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class NoteRepositry @Inject constructor(private val noteAPI: NoteAPI) {

    private val _noteResponseMuteableLivedata = MutableLiveData<ErrorHandling<List<Note>>>()
    val noteResponseLiveData: LiveData<ErrorHandling<List<Note>>>
        get() = _noteResponseMuteableLivedata
    private val _noteSuccessStatus = MutableLiveData<String>()
    val noteSuccess: LiveData<String>
        get() = _noteSuccessStatus


    suspend fun getNoteList() {
        _noteResponseMuteableLivedata.postValue(ErrorHandling.Loading())
        val response = noteAPI.getNote()
        handleNoteResponse(response)
    }

    suspend fun addNotes(addNote: NoteResponse) {
        val response = noteAPI.addNote(addNote)
        addNoteApiResponse(response)
    }

    suspend fun updateNote(noteId : String,note : NoteResponse){
        val response = noteAPI.editNote(noteId,note)
        updateNoteResponse(response)
    }
    suspend fun deleteNote(noteId : String){
        val response = noteAPI.deleteNote(noteId)
        deleteNoteResponse(response)
    }

    private fun deleteNoteResponse(response: Response<DeleteNoteResponse>) {
        if (response.isSuccessful && response.body() != null && response.body()!!.status) {
            _noteSuccessStatus.postValue(
                ErrorHandling.Success("Note Deleted Successfully").toString()
            )
        } else if (response.errorBody() != null) {
            val errorBody = JSONObject(response.errorBody()!!.charStream().readText())
            _noteSuccessStatus.postValue(errorBody.getString("message"))
        } else {
            _noteSuccessStatus.postValue("Some Thing Went Wrong")
        }
    }

    private fun updateNoteResponse(response: Response<UpdateNoteResponse>) {
        if (response.isSuccessful && response.body() != null && response.body()!!.status) {
            _noteSuccessStatus.postValue(
                ErrorHandling.Success("Note Updated Successfully").toString()
            )
        } else if (response.errorBody() != null) {
            val errorBody = JSONObject(response.errorBody()!!.charStream().readText())
            _noteSuccessStatus.postValue(errorBody.getString("message"))
        } else {
            _noteSuccessStatus.postValue("Some Thing Went Wrong")
        }
    }

    private fun addNoteApiResponse(response: Response<AddNoteResponse>) {
        if (response.isSuccessful && response.body() != null && response.body()!!.status) {
            _noteSuccessStatus.postValue(
                ErrorHandling.Success("Note Saved Successfully").toString()
            )
        } else if (response.errorBody() != null) {
            val errorBody = JSONObject(response.errorBody()!!.charStream().readText())
            _noteSuccessStatus.postValue(errorBody.getString("message"))
        } else {
            _noteSuccessStatus.postValue("Some Thing Went Wrong")
        }
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