package com.practice.finenote.modals.dbmodal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

private const val TABLENAME = "User"
@Entity(tableName = TABLENAME)
 class RoomDataBaseUserModal(
 @PrimaryKey(autoGenerate = true)
 @ColumnInfo(name = "UserID") private val userid : Int ,
 @ColumnInfo(name = "Username") private val username : String ,
 @ColumnInfo(name = "Email") private val email : String ,
 @ColumnInfo(name = "Password") private val password : String ,
)
