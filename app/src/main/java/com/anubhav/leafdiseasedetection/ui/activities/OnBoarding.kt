package com.anubhav.leafdiseasedetection.ui.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.anubhav.leafdiseasedetection.R
import com.anubhav.leafdiseasedetection.databinding.ActivityMainBinding
import com.anubhav.leafdiseasedetection.databinding.ActivityOnBoardingBinding
import com.anubhav.leafdiseasedetection.ui.fragments.FirstPage
import com.anubhav.leafdiseasedetection.ui.fragments.SecondPage
import com.anubhav.leafdiseasedetection.ui.fragments.ThirdPage

class OnBoarding : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardingBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)



        val fragments : ArrayList<Fragment> = arrayListOf(
            FirstPage(),
            SecondPage(),
            ThirdPage())

        val adapter = ViewPagerAdapter(fragments,this)
        binding.viewPager2.adapter  = adapter

        val sharedPreference =  getSharedPreferences("FIRST_TIME", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putBoolean("firstTime",false)
        editor.apply()

        Log.d("firstime",sharedPreference.getBoolean("firstTime",false).toString())
    }
}