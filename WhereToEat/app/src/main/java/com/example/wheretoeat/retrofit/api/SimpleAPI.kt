package com.example.wheretoeat.retrofit.api

import com.example.wheretoeat.retrofit.model.Post
import retrofit2.http.GET

interface SimpleAPI {
    @GET("api")
    suspend fun getPost(): Post
}