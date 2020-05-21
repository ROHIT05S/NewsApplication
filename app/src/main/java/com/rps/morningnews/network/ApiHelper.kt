package com.rps.morningnews.network

class ApiHelper(val apiService: ApiService) {

    suspend fun getArticles() = apiService.getArticleList()
    suspend fun getArticleViaCategory(country:String,category:String,apikey:String) = apiService.getArticleViaCategory(country,category,apikey)
}