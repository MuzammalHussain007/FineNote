package com.practice.finenote.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.modals.dbmodal.RoomDataBaseNoteModal

@Dao
interface RoomNoteInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddNote(note: RoomDataBaseNoteModal)

    @Delete
    suspend fun DeleteNote(note: RoomDataBaseNoteModal)

    @Update
    suspend fun EditNote(note: RoomDataBaseNoteModal)

    @Query("SELECT * FROM Note")
    fun GetAllNote(): ErrorHandling<LiveData<List<RoomDataBaseNoteModal>>>
}