package com.rps.morningnews.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.rps.morningnews.R
import com.rps.morningnews.R.layout.activity_splash
import com.rps.morningnews.databinding.ActivitySplashBinding
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : AppCompatActivity() {
    val SPLASH_TIME_OUT = 3000L
    lateinit var activitySplashBinding:ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySplashBinding = DataBindingUtil.setContentView(this,
            activity_splash
        )
        showWelcomeText()
        Handler().postDelayed({
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT)

    }

    fun showWelcomeText() {
        val sdf = SimpleDateFormat("HH:mm:ss");
        val str = sdf.format(Date());
        val hr = str.split(":");
        val hr1 = Integer.parseInt(hr[0]);
        Log.d("Splash Activity--",""+hr1)

        if (hr1 < 12) {
            activitySplashBinding.tvWelcomeText.text = "Welcome to \n NEWS@ MORNING"
        } else if (hr1 > 12 && hr1 < 17) {
            activitySplashBinding.tvWelcomeText.text = "Welcome to \n NEWS@ AFTERNOON"
        } else if (hr1 > 17 && hr1 < 20) {
            activitySplashBinding.tvWelcomeText.text = "Welcome to \n NEWS@ EVENING"
        }else if (hr1>20){
            activitySplashBinding.tvWelcomeText.text = "Welcome to \n NEWS@ NIGHT"
        }
    }
}
