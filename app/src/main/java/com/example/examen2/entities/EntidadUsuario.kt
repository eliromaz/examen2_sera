package com.example.examen2.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class EntidadUsuario(
    @PrimaryKey val id: Long,
    val name: String,
    val username: String,
    val email: String
)