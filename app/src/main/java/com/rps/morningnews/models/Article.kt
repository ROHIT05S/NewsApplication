package com.rps.morningnews.models

data class Article(val status:String,val totalResults:String,val articles:List<ArticleModel>) {
}