package com.example.examen2.utils

import com.example.examen2.entities.EntidadUsuario
import com.example.examen2.entities.UserEntity

// De UserEntity a EntidadUsuario
fun UserEntity.toEntidadUsuario(): EntidadUsuario {
    return EntidadUsuario(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email
    )
}

// De EntidadUsuario a UserEntity
fun EntidadUsuario.toUserEntity(): UserEntity {
    return UserEntity(
        id = this.id,
        name = this.name,
        username = this.username,
        email = this.email
    )
}