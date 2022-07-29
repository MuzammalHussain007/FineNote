package com.practice.finenote.modals.dbmodal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLENAME = "Note"

@Entity(tableName = TABLENAME)
 class RoomDataBaseNoteModal(
 @PrimaryKey(autoGenerate = true)
   val NoteID : Int ,
 @ColumnInfo(name = "Title")  val title : String ,
 @ColumnInfo(name = "Description")  val description : String ,
)
