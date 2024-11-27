package com.example.examen2.entities

data class CommentEntity(
    val postId: Long = 0,
    val id: Long = 0,
    val name: String,
    val email: String,
    val body: String
)
