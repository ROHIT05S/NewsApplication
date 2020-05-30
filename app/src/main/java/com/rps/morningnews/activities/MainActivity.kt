package com.rps.morningnews.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rps.morningnews.R
import com.rps.morningnews.adapters.NewsListAdapter
import com.rps.morningnews.databinding.ActivityMainBinding
import com.rps.morningnews.fragments.AboutFragment
import com.rps.morningnews.fragments.BottomSheetFragment
import com.rps.morningnews.fragments.FeedBackFragment
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
    val aboutFragment = AboutFragment.newInstance()
    val feedbackFragment = FeedBackFragment.newInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity","OnCreate")
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
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)

    }

    override fun onBottomSheetItemClicked1(data: String) {
        setupObservers(data.toLowerCase())
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_about -> {
            replaceFragment(activityMainBinding.frameLayoutMain,aboutFragment)
            true
        }
        R.id.action_feedback -> {
            replaceFragment(activityMainBinding.frameLayoutMain,feedbackFragment)
            true
        }
        R.id.action_setting ->{
            startActivityForResult( Intent(android.provider.Settings.ACTION_SETTINGS), 0);
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    fun replaceFragment(
        fragment: FrameLayout,
        selectedFragment: Fragment
    ) {
        Log.d("MainActivity","$selectedFragment")
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(fragment.id, selectedFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}
