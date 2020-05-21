package com.rps.morningnews.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.rps.morningnews.repository.MainRepository
import com.rps.morningnews.utils.Resource

import kotlinx.coroutines.Dispatchers

class NewsViewModel(val mainRepository: MainRepository) : ViewModel(){
    fun getArticles() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getArticleList()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
    fun getArticlesViaCategory(country:String,category:String,apikey:String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = mainRepository.getArticleViaCategory(country, category, apikey)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}