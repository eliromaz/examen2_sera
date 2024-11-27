package com.example.examen2.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.examen2.entities.EntidadUsuario
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: EntidadUsuario)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<EntidadUsuario>)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<EntidadUsuario>

    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()
}