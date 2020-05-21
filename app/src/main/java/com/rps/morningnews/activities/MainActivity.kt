package com.rps.morningnews.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rps.morningnews.R
import com.rps.morningnews.adapters.NewsListAdapter
import com.rps.morningnews.databinding.ActivityMainBinding
import com.rps.morningnews.fragments.BottomSheetFragment
import com.rps.morningnews.models.Article
import com.rps.morningnews.models.ArticleModel
import com.rps.morningnews.network.ApiHelper
import com.rps.morningnews.network.RetrofitBuilder
import com.rps.morningnews.utils.Resource
import com.rps.morningnews.utils.Status
import com.rps.morningnews.viewmodelfactory.ViewModelFactory
import com.rps.morningnews.viewmodels.NewsViewModel


class MainActivity : AppCompatActivity(),BottomSheetFragment.BottomSheetClickedListener {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var newsViewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )
        activityMainBinding.btnCategoriesList.setOnClickListener {
           val bottomSheet = BottomSheetFragment()
           bottomSheet.show(supportFragmentManager, "BottomSheetFragment")
        }
        setupViewModel()
        setupObservers(null)
    }
    private fun setupViewModel() {
        newsViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelper(
                    RetrofitBuilder.apiService
                )
            )
        ).get(NewsViewModel::class.java)
    }
    fun setupObservers(category:String?) {
        if (category.isNullOrEmpty()) {
            newsViewModel.getArticles().observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            showArticleList(resource)
                        }
                        Status.ERROR -> {
                            Log.d("MainActivityerror--", it.message.toString())
                        }
                        Status.LOADING -> {
                            activityMainBinding.progressBar.visibility=View.VISIBLE
                            activityMainBinding.newsLayout.visibility=View.GONE
                        }
                    }
                }
            })
        }else{
            newsViewModel.getArticlesViaCategory("in",category,"6cec9a688b7d481f9cebfca8ceca9a5a").observe(this, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            showArticleList(resource)
                            activityMainBinding.tvNewsHeading.text=category.toUpperCase()
                        }
                        Status.ERROR -> {
                            Log.d("MainActivityerror--", it.message.toString())
                        }
                        Status.LOADING -> {
                            activityMainBinding.progressBar.visibility=View.VISIBLE
                            activityMainBinding.newsLayout.visibility=View.GONE
                        }
                    }
                }
            })
        }
    }
    private fun showArticleList(resource: Resource<Article>) {
        activityMainBinding.progressBar.visibility=View.GONE
        activityMainBinding.newsLayout.visibility=View.VISIBLE
        val articles:List<ArticleModel>  = resource.data!!.articles
        val adapter = NewsListAdapter(articles, { article -> onArticleClicked(article) })
        activityMainBinding.rvNewsList.adapter = adapter
    }

    private fun onArticleClicked(article: ArticleModel) {
        val url: String = article.url
        val intent = Intent(applicationContext, NewsDetailActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun onBottomSheetItemClicked1(data: String) {
        setupObservers(data.toLowerCase())
    }


}
