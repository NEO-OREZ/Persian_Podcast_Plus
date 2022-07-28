package com.NEO_OREZ.persianpodcastplus


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.NEO_OREZ.persianpodcastplus.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
        val navController  = findNavController(R.id.fragmentContainerView)
        val appbarConfig = AppBarConfiguration(setOf(R.id.firsFragment, R.id.secondFragment, R.id.mainFragment))
        setupActionBarWithNavController(navController,appbarConfig )
        binding.bottomNavViewID.setupWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}