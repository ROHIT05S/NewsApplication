package com.rps.morningnews.repository

import com.rps.morningnews.network.ApiHelper

class MainRepository(val apiHelper: ApiHelper) {
    suspend fun getArticleList() = apiHelper.getArticles()
    suspend fun getArticleViaCategory(country:String,category:String,apikey:String) = apiHelper.getArticleViaCategory(country,category,apikey)

}