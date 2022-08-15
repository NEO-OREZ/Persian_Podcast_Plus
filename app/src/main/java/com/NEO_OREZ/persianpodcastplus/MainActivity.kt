package com.NEO_OREZ.persianpodcastplus


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.NEO_OREZ.persianpodcastplus.adapters.ViewPagerAdapter
import com.NEO_OREZ.persianpodcastplus.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        binding.viewPager2.adapter = adapter
        
        TabLayoutMediator(binding.tabLayout, binding.viewPager2){ tab,position-> when(position){
                0->{tab.text = "Main"}
                1->{tab.text = "First"}
                2->{tab.text = "Second"}
            }
        }.attach()

      //  val navController  = findNavController(R.id.fragmentContainerView)
//        val appbarConfig = AppBarConfiguration(setOf(R.id.firsFragment, R.id.secondFragment, R.id.mainFragment))
//        setupActionBarWithNavController(navController,appbarConfig )
//        binding.bottomNavViewID.setupWithNavController(navController)
    }

//    override fun onSupportNavigateUp(): Boolean {
//        //onBackPressed()
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}