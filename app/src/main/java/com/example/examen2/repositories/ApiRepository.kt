package com.example.examen2.repositories

import com.example.examen2.entities.CommentEntity
import com.example.examen2.entities.PostEntity
import com.example.examen2.entities.UserEntity

import com.example.examen2.network.ObjectRetrofit
import com.example.examen2.services.ApiService


class ApiRepository(private val apiService: ApiService = ObjectRetrofit.getInstanciaRetrofit) {

    suspend fun getAllUsers(): List<UserEntity>{
        return apiService.getAllUsers()
    }

    suspend fun getPostsByUserId(userId: Long): List<PostEntity> {
        return apiService.getPostsByUserId(userId)
    }

    suspend fun getCommentsByPostId(postId: Long): List<CommentEntity> {
        return apiService.getCommentsByPostId(postId)
    }




}