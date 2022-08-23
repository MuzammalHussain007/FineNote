package com.practice.finenote.api

import com.practice.finenote.responses.addNote.AddNoteResponse
import com.practice.finenote.responses.deleteNote.DeleteNoteResponse
import com.practice.finenote.responses.getNote.GetNoteResponse
import com.practice.finenote.responses.updateNote.UpdateNoteResponse
import retrofit2.Response
import retrofit2.http.*

interface NoteAPI {

   @PUT("notes/{noteid}")
   suspend fun editNote( @Path("noteid") id:String ,@Body updadateNote: UpdateNoteResponse) : Response<UpdateNoteResponse>
   @DELETE("notes/{noteid}")
   suspend fun deleteNote( @Path("noteid") id:String ) : Response<DeleteNoteResponse>
   @POST("notes/addnote")
   suspend fun addNote( @Body addNote: AddNoteResponse) : Response<AddNoteResponse>
   @GET("notes/getnote")
   suspend fun getNote() :  Response<List<GetNoteResponse>>
}