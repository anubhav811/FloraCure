package com.anubhav.Floracure.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.anubhav.FloraCure.databinding.ActivityOnBoardingBinding
import com.anubhav.Floracure.ui.fragments.FirstPage
import com.anubhav.Floracure.ui.fragments.SecondPage
import com.anubhav.Floracure.ui.fragments.ThirdPage
import com.google.android.material.tabs.TabLayoutMediator

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

        TabLayoutMediator(binding.tabMode, binding.viewPager2) { tab, position ->

        }.attach()



    }
}