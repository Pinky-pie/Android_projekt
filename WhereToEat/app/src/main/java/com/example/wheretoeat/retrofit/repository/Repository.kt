package com.example.wheretoeat.retrofit.repository

import com.example.wheretoeat.retrofit.api.RetrofitInstance
import com.example.wheretoeat.retrofit.model.Post

class Repository {

    suspend fun getPost(): Post {
        return RetrofitInstance.api.getPost()
    }

}