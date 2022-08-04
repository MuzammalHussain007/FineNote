package com.practice.finenote.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practice.finenote.modals.dbmodal.RoomDataBaseNoteModal
import com.practice.finenote.modals.dbmodal.RoomDataBaseUserModal

@Database(entities = [RoomDataBaseNoteModal::class, RoomDataBaseUserModal::class], version = 1, exportSchema = false)
abstract class RoomConnection: RoomDatabase() {


}