package com.anubhav.leafdiseasedetection.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.findFragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
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
//        navController = nav_host_fragment.getFragment<NavHostFragment>().navController

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        binding.navView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.item1 ->
//            }
//        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)

    }
}