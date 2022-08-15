package com.practice.finenote.api

import androidx.lifecycle.LiveData
import com.practice.finenote.modals.UserRequest
import com.practice.finenote.modals.UserResponse
import com.practice.finenote.responses.addNote.AddNoteResponse
import com.practice.finenote.responses.deleteNote.DeleteNoteResponse
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.updateNote.UpdateNoteResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface NoteAPI {

   @POST("notes/{noteid}")
   suspend fun editNote( @Path("noteid") id:String ,@Body updadateNote: UpdateNoteResponse) : Response<UpdateNoteResponse>

   @POST("notes/{noteid}")
   suspend fun deleteNote( @Path("noteid") id:String ) : Response<DeleteNoteResponse>
   suspend fun addNote( @Body addNote: AddNoteResponse) : Response<AddNoteResponse>
   fun getNote() :  ErrorHandling<LiveData<Response<GetNoteResponse>>>
}