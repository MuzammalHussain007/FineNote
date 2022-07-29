package com.practice.finenote.modals.dbmodal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLENAME = "Note"

@Entity(tableName = TABLENAME)
 class RoomDataBaseNoteModal(
 @PrimaryKey(autoGenerate = true)
 @ColumnInfo(name = "NoteID") private val NoteID : Int ,
 @ColumnInfo(name = "Title") private val title : String ,
 @ColumnInfo(name = "Description") private val description : String ,
)
