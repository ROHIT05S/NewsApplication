package com.rps.morningnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rps.morningnews.R
import com.rps.morningnews.databinding.ActivityMainBinding
import com.rps.morningnews.databinding.ActivityNewsDetailBinding

class NewsDetailActivity : AppCompatActivity() {
    lateinit var activityNewsDetailBinding: ActivityNewsDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityNewsDetailBinding = DataBindingUtil.setContentView(this,
            R.layout.activity_news_detail
        )
        val intent = intent
        val url = intent.extras?.get("url")
        activityNewsDetailBinding.newsDetailWebview.loadUrl(url as String?)
    }
}
