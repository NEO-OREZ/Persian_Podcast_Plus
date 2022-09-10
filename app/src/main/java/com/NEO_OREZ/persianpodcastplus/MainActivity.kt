package com.NEO_OREZ.persianpodcastplus


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.NEO_OREZ.persianpodcastplus.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("logMainActivity_00","Main Activity onCreate started ")
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
         val navController by lazy {
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerViewID1) as NavHostFragment).navController
        }
        setupActionBarWithNavController(navController)

    }
    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("logMainActivity_01","on back press")
    }
}