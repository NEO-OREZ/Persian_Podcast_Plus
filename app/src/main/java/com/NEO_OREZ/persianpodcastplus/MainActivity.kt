package com.NEO_OREZ.persianpodcastplus


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.NEO_OREZ.persianpodcastplus.Apollo.CallRequest
import com.NEO_OREZ.persianpodcastplus.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var navController0: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("logMainActivity_0","Main Activity onCreate started ")
        /////view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ////////////////
        lifecycleScope.launch(Dispatchers.IO) { CallToken() }

        navController0 = findNavController(R.id.fragmentContainerViewID1)
         val navController by lazy {
            (supportFragmentManager.findFragmentById(R.id.fragmentContainerViewID1) as NavHostFragment).navController
        }
        setupActionBarWithNavController(navController0)
        Log.d("logMainActivity_000",navController.toString())
        Log.d("logMainActivity_001",navController0.toString())
        //Log.d("logMainActivity_0000",navController.toString())
        //setupActionBarWithNavController(navController)
    }

    suspend fun CallToken() {
        Log.d("logMainActivity_00", "CallToken()")
        val token = CallRequest().apolloToken()
        Log.d("logMainActivity_01", token)
        val sharedPreferences = this.getSharedPreferences("KeySave", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply{
            putString("key0", token)
        }.apply()
        Log.d("logMainActivity_02", "saveData()")
    }

//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return navController.navigateUp() || super.onSupportNavigateUp()
//    }
}