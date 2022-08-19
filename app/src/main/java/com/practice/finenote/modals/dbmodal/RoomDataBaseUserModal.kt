package com.practice.finenote.modals.dbmodal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLENAME = "User"
@Entity(tableName = TABLENAME)
 class RoomDataBaseUserModal(
 @PrimaryKey(autoGenerate = true)
   val userid : Int ,
 @ColumnInfo(name = "Username")  val username : String ,
 @ColumnInfo(name = "Email")  val email : String ,
 @ColumnInfo(name = "Password")  val password : String ,
)
