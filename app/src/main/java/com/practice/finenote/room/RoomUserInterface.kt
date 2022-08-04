package com.practice.finenote.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.practice.finenote.api.ErrorHandling
import com.practice.finenote.modals.dbmodal.RoomDataBaseUserModal

@Dao
interface RoomUserInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddUser(user: RoomDataBaseUserModal)

    @Delete
    suspend fun DeleteUser(user: RoomDataBaseUserModal)

    @Update
    suspend fun EditUser(user: RoomDataBaseUserModal)

    fun GetAllUser(): ErrorHandling<LiveData<List<RoomDataBaseUserModal>>>
}