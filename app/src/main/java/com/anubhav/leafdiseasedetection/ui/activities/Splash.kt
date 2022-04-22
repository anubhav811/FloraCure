package com.anubhav.leafdiseasedetection.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.anubhav.leafdiseasedetection.databinding.ActivityMainBinding
import com.anubhav.leafdiseasedetection.databinding.ActivitySplashBinding

class Splash : AppCompatActivity() {
    private lateinit var sharedPreference : SharedPreferences
    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        sharedPreference = getSharedPreferences("FIRST_TIME", Context.MODE_PRIVATE)
        Log.d("firstime",sharedPreference.getBoolean("firstTime",false).toString())

        Handler().postDelayed({

            val intent = if (sharedPreference.getBoolean("firstTime", false)) {
                Intent(this@Splash, MainActivity::class.java)
            } else {
                Intent(this@Splash, OnBoarding::class.java)
            }
            startActivity(intent)
            Log.d("firstime",sharedPreference.getBoolean("firstTime",false).toString())

            finish()
        }, 1500)

    }
}