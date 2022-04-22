package com.anubhav.leafdiseasedetection.ui.activities

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.anubhav.leafdiseasedetection.R
import com.anubhav.leafdiseasedetection.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var  toggle : ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding

    lateinit var navController : NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view =  binding.root
        setContentView(view)

        toggle = ActionBarDrawerToggle(this,binding.drawerLayout,0,0)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}