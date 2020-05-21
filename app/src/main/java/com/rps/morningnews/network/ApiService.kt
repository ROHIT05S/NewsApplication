package com.rps.morningnews.network

import com.rps.morningnews.models.Article
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=in&apiKey=6cec9a688b7d481f9cebfca8ceca9a5a")
    suspend fun getArticleList(): Article

    @GET("top-headlines")
    suspend fun getArticleViaCategory(
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("apiKey") apiKey:String
    ):Article

}