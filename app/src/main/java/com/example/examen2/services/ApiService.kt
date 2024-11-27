package com.example.examen2.services

import com.example.examen2.entities.CommentEntity
import com.example.examen2.entities.PostEntity
import com.example.examen2.entities.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getAllUsers(): List<UserEntity>

    @GET("posts")
    suspend fun getPostsByUserId(
        @Query("userId") userId: Long
    ): List<PostEntity>

    @GET("comments")
    suspend fun getCommentsByPostId(@Query("postId") postId: Long
    ): List<CommentEntity>
}