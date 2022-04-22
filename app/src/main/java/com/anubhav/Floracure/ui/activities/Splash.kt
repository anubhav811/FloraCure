package com.anubhav.Floracure.ui.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.anubhav.FloraCure.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var sharedPreference : SharedPreferences
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreference = getSharedPreferences("onBoarding", MODE_PRIVATE);
        val firstTime = sharedPreference.getBoolean("firstTime",true)

        Handler().postDelayed({

            val intent = if (firstTime) {
                val editor = sharedPreference.edit()
                editor.putBoolean("firstTime",false)
                editor.apply()
                Intent(this@Splash, OnBoarding::class.java)
            } else {
                Intent(this@Splash, MainActivity::class.java)
            }
            startActivity(intent)
            finish()
        }, 1500)

    }
}