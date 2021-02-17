package com.example.taskmodemoapp.retrofit

import com.example.taskmodemoapp.model.Post
import retrofit2.http.GET

interface AppRetrofit {

    @GET("posts")
    suspend fun getPost() : List<PostNetworkEntity>

    @GET("comments")
    suspend fun getComment() : List<CommentNetworkEntity>
}