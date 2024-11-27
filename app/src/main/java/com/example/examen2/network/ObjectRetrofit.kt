package com.example.examen2.network

import com.example.examen2.services.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ObjectRetrofit {
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val getInstanciaRetrofit: ApiService by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}