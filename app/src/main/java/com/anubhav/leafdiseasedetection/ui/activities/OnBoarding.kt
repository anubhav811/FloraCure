package com.anubhav.leafdiseasedetection.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anubhav.leafdiseasedetection.R
import com.anubhav.leafdiseasedetection.databinding.ActivityMainBinding
import com.anubhav.leafdiseasedetection.databinding.ActivityOnBoardingBinding

class OnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)
    }
}