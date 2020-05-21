package com.rps.morningnews.models

data class ArticleModel( val source : NewsSource ,val author:String, val title: String , val description:String,
                        val url:String , val urlToImage: String , val publishedAt:String,val content:String) {
}